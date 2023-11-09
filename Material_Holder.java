package Image.To.Text;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class Material_Holder extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userlist;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_holder);

        initData();
        initRecyclerView();

        EditText editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<ModelClass> filteredList = new ArrayList<>();

        for (ModelClass item : userlist) {
            if (item.getTextviewpngrommet().toLowerCase().contains(text.toLowerCase())
                    || item.getTextviewgrommet().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void initData() {
        userlist = new ArrayList<>();
        userlist.add(new ModelClass(R.drawable.background, "Grommet", "AE059345-0790","______________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Holder Brush", "AE059281-0490","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Plate Terminal E", "AE059335-2750","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Plate Terminal LO", "AE059335-2310","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Plate Terminal Hi", "AE059335-2320","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Plate Terminal COM", "AE059335-2290","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Plate Insulator", "AE059164-1420","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Breaker (Brown)", "AE059571-0630","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Breaker (Blue)", "AE059571-0620","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Breaker (Grey)", "AE059571-0610","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Plate Terminal HI", "AE059335-1851","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Plate Terminal LO", "AE059335-1841","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Coil Choke", "AE059566-0200","____________________________________________________________________________________________________"));
        userlist.add(new ModelClass(R.drawable.background, "Spring Coil", "AE059252-0210","____________________________________________________________________________________________________"));
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userlist, new Adapter.ItemClickListener() {
            @Override
            public void onItemClick(ModelClass modelClass) {
                // Start the new activity here
                Intent intent = new Intent(Material_Holder.this, MainActivity.class);
                    intent.putExtra("textviewpngrommet", modelClass.getTextviewpngrommet());
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(adapter);
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}