package wlfrotaneto.studio.com.lanchonetedelcia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Produto> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listProdutos = findViewById(R.id.listProdutos);
        obterListaDeProdutos(listProdutos);
        listProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalheIntent = new Intent(MainActivity.this, DetalheActivity.class);
                detalheIntent.putExtra("produto", listaProdutos.get(position));
                startActivity(detalheIntent);
            }
        });
    }

    private void obterListaDeProdutos(final ListView listProdutos){
        AsyncHttpClient client = new AsyncHttpClient();
        String uri = "https://patra-backend.appspot.com/produtos";
        client.get(uri, new TextHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                        Log.d("AsyncHttpClient", "response = " + response);
                        obterListaProdutos(response);
                        setListaDeProdutosNoComponente(listProdutos, listaProdutos);
                    }
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable){
                        Log.d("AsyncHttpClient", "response = " + response);
                    }
                }
        );
    }

    private void obterListaProdutos(String json){
        Gson gson = new GsonBuilder().create();
        Produto[] produtos = gson.fromJson(json , Produto[].class);
        listaProdutos = new ArrayList(Arrays.asList(produtos));
    }

    private void setListaDeProdutosNoComponente(ListView listProdutos, ArrayList<Produto> produtos){
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.item_produto, R.id.item_name);
        adapter.clear();
        for(Produto produto : produtos) {
            adapter.add(produto.getNome());
        }
        listProdutos.setAdapter(adapter);
    }
}
