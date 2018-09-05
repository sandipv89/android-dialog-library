package in.tabdevelopers.mylibrary;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TabDialog {

    public static void showDialogList(AppCompatActivity activity, List<Item> items, boolean isMulti, final OnResultListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_items, null);
        builder.setView(dialogView);

        Button btnOk = dialogView.findViewById(R.id.btnOk);
        RecyclerView rvItems = dialogView.findViewById(R.id.rvItems);

        final ItemAdapter itemAdapter = new ItemAdapter(activity, items, isMulti);
        rvItems.setLayoutManager(new LinearLayoutManager(activity));
        rvItems.setAdapter(itemAdapter);

        final AlertDialog dialog = builder.create();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                listener.OnResult(itemAdapter.getResult());
            }
        });

        dialog.show();

    }

    private static class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
        private AppCompatActivity activity;
        private List<Item> items = new ArrayList<>();
        private boolean isMulti = false;
        private List<Item> resultItems = new ArrayList<>();

        public ItemAdapter(AppCompatActivity activity, List<Item> items, boolean isMulti) {
            this.activity = activity;
            this.items = items;
            this.isMulti = isMulti;
        }

        @NonNull
        @Override
        public ItemAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemHolder(LayoutInflater.from(activity).inflate(R.layout.item_list, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemAdapter.ItemHolder holder, int position) {
            Item item = items.get(position);
            holder.tvTitle.setText(item.getTitle());
            holder.tvSubTitle.setText(item.getSubTitle());
            if (item.isChecked()) {
                holder.llParent.setBackgroundColor(activity.getResources().getColor(R.color.grey_300));
            } else {
                holder.llParent.setBackgroundColor(activity.getResources().getColor(R.color.white));
            }

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public List<Item> getResult() {
            return resultItems;
        }

        class ItemHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvSubTitle;
            LinearLayout llParent;

            ItemHolder(View itemView) {
                super(itemView);

                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvSubTitle = itemView.findViewById(R.id.tvSubTitle);
                llParent = itemView.findViewById(R.id.llParent);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isMulti) {
                            for (int i = 0; i < items.size(); i++) {
                                if (i == getAdapterPosition()) {
                                    items.get(i).setChecked(true);
                                } else {
                                    items.get(i).setChecked(false);
                                }
                            }

                            notifyDataSetChanged();
                        } else {
                            if (items.get(getAdapterPosition()).isChecked()) {
                                items.get(getAdapterPosition()).setChecked(false);
                            } else {
                                items.get(getAdapterPosition()).setChecked(true);
                            }

                            notifyDataSetChanged();
                        }

                        resultItems.clear();
                        for (int i = 0; i < items.size(); i++) {
                            if (items.get(i).isChecked()) {
                                resultItems.add(items.get(i));
                            }
                        }

                    }
                });


            }
        }
    }
}