package com.example.widgetsrcvp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // Declaración de variables para los componentes de la interfaz de usuario
    ToggleButton toggleButton; // Botón de encendido/apagado
    CheckBox checkBox;         // Caja de verificación
    ListView listView;         // Lista de elementos
    DatePicker datePicker;     // Selector de fecha

    @Override
// La anotación @Override indica que estamos sobrescribiendo el método onCreate() de la clase AppCompatActivity.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Llama al método onCreate() de la clase base para que la actividad se inicialice correctamente.
        setContentView(R.layout.activity_main); // Establece el diseño de la actividad, cargando el archivo XML 'activity_main' como la interfaz de usuario.

        // Asignación de los componentes de la interfaz de usuario (definidos en el archivo XML) a variables Java.
        toggleButton = findViewById(R.id.toggle_button); // Vincula el ToggleButton definido en el archivo XML con su representación en el código.
        checkBox = findViewById(R.id.check_box);         // Vincula el CheckBox del layout XML con la variable checkBox en Java.
        listView = findViewById(R.id.list_view);         // Vincula el ListView del archivo XML con su variable correspondiente.
        datePicker = findViewById(R.id.date_picker);     // Vincula el DatePicker del archivo XML con la variable datePicker.

        // Configuración del comportamiento del ToggleButton cuando el usuario lo activa o desactiva.
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Verifica si el ToggleButton está activado.
            if (isChecked) {
                // Si está activado, muestra un mensaje indicando que está en "ON".
                Toast.makeText(MainActivity.this, "Toggle ON", Toast.LENGTH_SHORT).show();
            } else {
                // Si está desactivado, muestra un mensaje indicando que está en "OFF".
                Toast.makeText(MainActivity.this, "Toggle OFF", Toast.LENGTH_SHORT).show();
            }
        });

        // Configuración del CheckBox para mostrar un mensaje cuando se marca o desmarca.
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Verifica si el CheckBox está marcado.
            if (isChecked) {
                // Si está marcado, muestra un mensaje de "CheckBox Marcado".
                Toast.makeText(MainActivity.this, "CheckBox Marcado", Toast.LENGTH_SHORT).show();
            } else {
                // Si está desmarcado, muestra un mensaje de "CheckBox Desmarcado".
                Toast.makeText(MainActivity.this, "CheckBox Desmarcado", Toast.LENGTH_SHORT).show();
            }
        });

        // Datos de ejemplo para mostrar en el ListView.
        String[] listItems = {"Pasear al perro", "Descongelar la carne", "Ir al banco", "Limpiar la casa"}; // Arreglo con elementos de ejemplo para la lista.
        // Crea un adaptador para conectar el ListView con los datos.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter); // Asigna el adaptador al ListView para que muestre los elementos.

        // Configuración del DatePicker para mostrar un mensaje con la fecha seleccionada.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Verifica si la versión de Android es igual o superior a Oreo.
            datePicker.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
                // Captura la fecha seleccionada y la formatea en una cadena de texto.
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                // Muestra un mensaje con la fecha seleccionada.
                Toast.makeText(MainActivity.this, "Fecha seleccionada: " + date, Toast.LENGTH_SHORT).show();
            });
        }

        // Configuración de un botón para mostrar un AlertDialog cuando se presiona.
        findViewById(R.id.show_dialog_button).setOnClickListener(v -> {
            // Crea y muestra un cuadro de diálogo con un mensaje de confirmación.
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Alerta") // Establece el título del diálogo.
                    .setMessage("¿Estás seguro?") // Establece el mensaje del diálogo.
                    .setPositiveButton("Sí", (dialog, which) -> {
                        // Acción cuando el usuario selecciona "Sí" en el diálogo.
                        Toast.makeText(MainActivity.this, "Seleccionaste Sí", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // Cierra el diálogo si el usuario selecciona "No".
                        dialog.dismiss();
                    })
                    .show(); // Muestra el diálogo en pantalla.
        });
    }
}