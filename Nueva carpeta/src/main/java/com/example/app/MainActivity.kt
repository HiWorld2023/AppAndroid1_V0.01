package com.example.app

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.AppTheme
import androidx.compose.runtime.snapshots.SnapshotStateMap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Views()
            }
        }
    }
}

data class Persona(val nombre: String, val descripcion: String)

@Composable
fun Views() {
    val personas = listOf(
        Persona("Mateo Rosario Troncoso", "Diseña y desarrolla aplicaciones móviles para Android, asegurando su compatibilidad y rendimiento óptimo."),
        Persona("Juan Pérez", "Diseña, desarrolla y mantiene software de alta calidad, aplicando principios de ingeniería para garantizar su escalabilidad y fiabilidad."),
        Persona("Ana Gómez", "Crea interfaces de usuario intuitivas y atractivas, centradas en la experiencia del usuario para mejorar la interacción con los productos digitales."),
        Persona("Mateo Rosario Troncoso", "Analiza e interpreta datos para tomar decisiones informadas, utilizando herramientas de análisis y visualización de datos."),
        Persona("Juan Pérez", "Protege sistemas informáticos contra amenazas, implementando medidas de seguridad y monitoreando continuamente para prevenir vulnerabilidades."),
        Persona("Ana Gómez", "Mejora la colaboración entre desarrollo y operaciones, automatizando procesos y asegurando la entrega continua de software de alta calidad."),
        Persona("Mateo Rosario Troncoso", "Diseña la estructura general de sistemas de software, garantizando su escalabilidad, flexibilidad y mantenibilidad a largo plazo."),
        Persona("Juan Pérez", "Desarrolla soluciones basadas en IA y ML, aplicando algoritmos avanzados para resolver problemas complejos y mejorar la toma de decisiones."),
        Persona("Ana Gómez", "Coordina y gestiona proyectos desde su inicio hasta su finalización, asegurando que se cumplan los objetivos y plazos establecidos."),
        Persona("Mateo Rosario Troncoso", "Diseña y administra redes informáticas, garantizando su seguridad, rendimiento y conectividad."),
        Persona("Juan Pérez", "Crea aplicaciones web dinámicas y responsivas, utilizando tecnologías como HTML, CSS y JavaScript."),
        Persona("Ana Gómez", "Diseña y administra sistemas informáticos, asegurando su integridad y eficiencia operativa."),
        Persona("Mateo Rosario Troncoso", "Crea elementos visuales atractivos y coherentes para comunicar mensajes efectivamente a través de diferentes medios."),
        Persona("Juan Pérez", "Identifica necesidades del negocio y desarrolla soluciones para mejorar procesos y aumentar la eficiencia organizacional."),
        Persona("Ana Gómez", "Desarrolla estrategias de marketing en línea para aumentar la visibilidad y el engagement de la marca en el mercado digital."),
        Persona("Mateo Rosario Troncoso", "Diseña y administra sistemas de gestión de datos, asegurando su integridad y accesibilidad para el análisis."),
        Persona("Juan Pérez", "Diseña y gestiona la infraestructura tecnológica de una organización, garantizando su escalabilidad y seguridad."),
        Persona("Ana Gómez", "Protege la información confidencial de la organización, implementando políticas y controles de seguridad efectivos."),
        Persona("Mateo Rosario Troncoso", "Supervisa y optimiza las operaciones diarias de una organización, asegurando la eficiencia y productividad."),
        Persona("Juan Pérez", "Garantiza la calidad de los productos o servicios, implementando procesos y pruebas para asegurar su conformidad con los estándares."),
        Persona("Ana Gómez", "Mejora la experiencia del cliente a través de la comprensión de sus necesidades y la implementación de soluciones personalizadas.")
    )

    RowsViews(personas)
}

@Composable
fun RowsViews(personas: List<Persona>) {
    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() } // Usamos SnapshotStateMap

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(10.dp)
    ) {
        itemsIndexed(personas) { index, persona ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                myImage()
                myTexts(
                    nombre = persona.nombre,
                    descripcion = persona.descripcion,
                    index = index,
                    expandedStates = expandedStates
                )
            }
        }
    }
}

@Composable
fun myTexts(
    nombre: String,
    descripcion: String,
    index: Int,
    expandedStates: SnapshotStateMap<Int, Boolean>
) {
    val expanded = expandedStates.getOrDefault(index, false)

    Column(modifier = Modifier.padding(1.dp).clickable {
        expandedStates[index] = !expanded
    }) {
        myText(nombre, MaterialTheme.colors.primary)
        Spacer(modifier = Modifier.height(3.dp))
        myText(
            descripcion,
            MaterialTheme.colors.onBackground,
            lines = if (expanded) Int.MAX_VALUE else 1,
            overflow = if (expanded) TextOverflow.Visible else TextOverflow.Ellipsis
        )
    }
}

@Composable
fun myText(text: String, color: Color, lines: Int = Int.MAX_VALUE, overflow: TextOverflow = TextOverflow.Visible) {
    Text(text, color = color, maxLines = lines, overflow = overflow)
}

@Composable
fun myImage() {
    Image(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "Imagen de perfil",
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.surface)
            .size(64.dp)
            .padding(8.dp)
    )
}

@Composable
fun PreviewText() {
    AppTheme {
        Views()
    }
}

@Preview
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewTextDark() {
    AppTheme {
        Views()
    }
}
