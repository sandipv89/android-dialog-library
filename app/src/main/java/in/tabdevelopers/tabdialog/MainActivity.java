package in.tabdevelopers.tabdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.tabdevelopers.mylibrary.Item;
import in.tabdevelopers.mylibrary.OnResultListener;
import in.tabdevelopers.mylibrary.TabDialog;

public class MainActivity extends AppCompatActivity implements OnResultListener {
    private AppCompatActivity activity = this;
    private List<Item> items = new ArrayList<>();
    private Button btnShowDialog;
    private RadioGroup rgOptions;
    private TextView tvResult;
    private OnResultListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listener = this;

        initViews();
        updateViews();
        setListeners();

        prepareData();

    }

    private void prepareData() {
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setTitle("Title" + i);
            item.setSubTitle("SubTitle" + i);
            items.add(item);
        }
    }

    private void setListeners() {

        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isMulti = false;
                if (rgOptions.getCheckedRadioButtonId() == R.id.rbSingle) {
                    isMulti = false;
                } else if (rgOptions.getCheckedRadioButtonId() == R.id.rbMulti) {
                    isMulti = true;
                }
                TabDialog.showDialogList(activity, items, isMulti, listener);
            }
        });

    }

    private void updateViews() {

    }

    private void initViews() {

        btnShowDialog = findViewById(R.id.btnShowDialog);
        rgOptions = findViewById(R.id.rgOptions);
        tvResult = findViewById(R.id.tvResult);
    }

    @Override
    public void OnResult(List<Item> items) {
        String result = "";
        for (int i = 0; i < items.size(); i++) {
            result = String.format("%s%s\n%s\n\n",
                    result, items.get(i).getTitle(), items.get(i).getSubTitle());
        }

        tvResult.setText(result);
    }
}
