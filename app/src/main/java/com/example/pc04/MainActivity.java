package com.example.pc04;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.view.inputmethod.InputConnectionCompat;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText nombre, apellido,género,edad,dirección,telefono;
    Button registrar;
    ProgressBar progressBar;
    FirebaseAuth autentificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializamos firebase
        autentificacion = FirebaseAuth.getInstance();

        // casteamos de los onjetos (widgets)

        nombre=(EditText) findViewById(R.id.Nombre);
        apellido=(EditText) findViewById(R.id.Apellido);
        género=(EditText) findViewById(R.id.Género);
        edad=(EditText) findViewById(R.id.Edad);
        dirección=(EditText) findViewById(R.id.Dirección);
        telefono=(EditText) findViewById(R.id.Telefono);

        registrar= (Button) findViewById(R.id.Mostrar);

        registrar.setOnClickListener(this);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre= nombre.getText().toString().trim();
                apellido = apellido.getText().toString().trim();
                género = género.getText().toString().trim();
                edad = edad.getText().toString().trim();
                dirección = dirección.getText().toString().trim();
                telefono = telefono.getText().toString().trim();

                progressBar.setVisibility(view.VISIBLE);

                autentificacion.createUserWithEmailAndPassword(nombre,apellido,género,edad,dirección,telefono)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                // si la creacion del usuario damos un mesaje de error

                                if (task.isSuccessful()) {

                                    progressBar.setVisibility(view.INVISIBLE);
                                    Toast.makeText(MainActivity.this, "usuario registrado", Toast.LENGTH_SHORT).show();
                                } else {
                                    progressBar.setVisibility(view.INVISIBLE);
                                    Toast.makeText(MainActivity.this, "no se pudo registar al usuario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "ERROR:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }); }

        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre= nombre.getText().toString().trim();
                apellido = apellido.getText().toString().trim();
                género = género.getText().toString().trim();
                edad = edad.getText().toString().trim();
                dirección = dirección.getText().toString().trim();
                telefono = telefono.getText().toString().trim();

                autentificacion.signInWithEmailAndPassword(nombre,apellido,género,edad,dirección,telefono)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"usuario")
                                }
                            }
                        })
            }
        });
    }
    public void LimpiarCajasDeTextos(){

        nombre.setText("");
        apellido.setText("");
        género.setText("");
        edad.setText("");
        dirección.setText("");
        telefono.setText("");




    }
}