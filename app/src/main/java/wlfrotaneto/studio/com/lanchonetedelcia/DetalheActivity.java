package wlfrotaneto.studio.com.lanchonetedelcia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        Intent intent = getIntent();
        if (intent.hasExtra("produto")){
            Produto produto = (Produto) intent.getSerializableExtra("produto");
            setProduto(produto);
        }
    }

    private void setProduto(Produto produto){
        TextView textNome = findViewById(R.id.textNome);
        TextView textDescricao = findViewById(R.id.textDescricao);
        TextView textPreco = findViewById(R.id.textPreco);
        ImageView imageView = findViewById(R.id.imageProduto);
        textNome.setText(produto.getNome());
        textDescricao.setText(produto.getDescricao());
        textPreco.setText(produto.getPreco());
        Picasso.get().load(produto.getImagem()).into(imageView);
    }
}
