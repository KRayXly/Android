package cn.edu.hznu.listviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    private List<Fruit> filterList =new ArrayList<>();
    //private String[] data ={"Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry","Mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        filterList=fruitList;
        FruitAdapter adapter=new FruitAdapter(filterList);
        recyclerView.setAdapter(adapter);

        Button sreachbt =(Button) findViewById(R.id.sre);

        sreachbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input =(EditText) findViewById(R.id.input);
                String charString = input.getText().toString();
                if (charString.isEmpty()) {
                    filterList=fruitList;
                } else {
                    List<Fruit> filteredList = new ArrayList<>();
                    for (Fruit fruit :fruitList) {
                        if (fruit.getName().toLowerCase().contains(charString.toLowerCase())
                                ||fruit.getChinese().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(fruit);
                        }
                    }
                    filterList=filteredList;
                    FruitAdapter adapter=new FruitAdapter(filterList);
                    recyclerView.setAdapter(adapter);
                }

            }
        });
    }

    private void initFruits(){
        for(int i = 0;i < 2;i++){
            Fruit Apple = new Fruit("Apple","苹果",R.drawable.apple_pic);
            fruitList.add(Apple);
            Fruit Banana = new Fruit("Banana","香蕉",R.drawable.banana_pic);
            fruitList.add(Banana);
            Fruit Orange = new Fruit("Orange","橘子",R.drawable.orange_pic);
            fruitList.add(Orange);
            Fruit Watermelon = new Fruit("Watermelon","西瓜",R.drawable.watermelon_pic);
            fruitList.add(Watermelon);
            Fruit Pear = new Fruit("Pear","梨子",R.drawable.pear_pic);
            fruitList.add(Pear);
            Fruit Grape = new Fruit("Grape","葡萄",R.drawable.grape_pic);
            fruitList.add(Grape);
            Fruit Pineapple = new Fruit("Pineapple","菠萝",R.drawable.pineapple_pic);
            fruitList.add(Pineapple);
            Fruit Strawberry = new Fruit("Strawberry","草莓",R.drawable.strawberry_pic);
            fruitList.add(Strawberry);
            Fruit Cherry = new Fruit("Cherry","樱桃",R.drawable.cherry_pic);
            fruitList.add(Cherry);
            Fruit Mango = new Fruit("Mango","芒果",R.drawable.mango_pic);
            fruitList.add(Mango);
        }
    }
}