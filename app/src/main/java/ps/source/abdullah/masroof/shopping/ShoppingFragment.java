package ps.source.abdullah.masroof.shopping;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import ps.source.abdullah.masroof.R;

public class ShoppingFragment extends Fragment {

    private ArrayList items = new ArrayList<>();
    private ShoppingAdapter adapter;
    private RecyclerView recyclerView;
    private AlertDialog.Builder alertDialog;
    private EditText et_name;
    private int edit_position;
    private View view;
    private boolean add = false;
    private Paint p = new Paint();

    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_shopping, container, false);
        initViews(rootView);
        initDialog(rootView);

        FloatingActionButton floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView();
                add = true;
                alertDialog.setTitle("اضافة الى المشتريات:");
                et_name.setText("");
                alertDialog.show();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void initViews(View rootView) {

        recyclerView = (RecyclerView) rootView.findViewById(R.id.items_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ShoppingAdapter(items);
        recyclerView.setAdapter(adapter);
        items.add("طحين حلويات");
        items.add("أسماك");
        items.add("زيت ذرة");
        items.add("معجون أسنان");
        items.add("دوا غسيل");
        adapter.notifyDataSetChanged();
        initSwipe(rootView);
    }

    private void initSwipe(View rootView) {
        final Context context = rootView.getContext();
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    adapter.removeItem(position);
                } else {
                    removeView();
                    edit_position = position;
                    alertDialog.setTitle("تعديل المنتج: ");
                    et_name.setText(items.get(position).toString());
                    alertDialog.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX > 0) {
                        p.setColor(getResources().getColor(R.color.colorSuccess));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                        c.drawRect(background, p);
                        if (dX > 10) {
                            icon = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_mode_edit_white_48dp);
                            RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                            c.drawBitmap(icon, null, icon_dest, p);
                        }
                    } else {
                        p.setColor(getResources().getColor(R.color.colorDangerDark));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        if (dX < -30) {
                            icon = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_delete_sweep_white_48dp);
                            RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                            c.drawBitmap(icon, null, icon_dest, p);
                        }
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void removeView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    private void initDialog(final View rootView) {
        alertDialog = new AlertDialog.Builder(getContext());
        view = getLayoutInflater(null).inflate(R.layout.item_shopping_dialog_layout, null);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("حفظ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String message = "";

                if (add) {
                    add = false;
                    adapter.addItem(et_name.getText().toString());
                    dialog.dismiss();
                    message = "تمت الإضافة على قائمة المشترايت";

                } else {
                    items.set(edit_position, et_name.getText().toString());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                    message = "تمت تعديل قائمة المشترايت";
                }

                Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        et_name = (EditText) view.findViewById(R.id.et_name);
    }

}
