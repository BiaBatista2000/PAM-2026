package com.example.inventario

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventario.ui.theme.InventarioTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.filled.ArrowBack

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            InventarioTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F1EC)
                ) {

                    NavigationScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    navController: NavHostController
) {

    var usuario by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    var erro by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFFFD180),
                        Color(0xFFFFD180),
                        Color(0xFFFFD180),
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFB74D)),

                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Inventory2,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Inventário Corporativo",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF3E2723)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Entre com sua conta da empresa",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    OutlinedTextField(
                        value = usuario,
                        onValueChange = {
                            usuario = it
                        },

                        modifier = Modifier.fillMaxWidth(),

                        label = {
                            Text("Usuário")
                        },

                        leadingIcon = {

                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null
                            )
                        },

                        shape = RoundedCornerShape(18.dp)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    OutlinedTextField(
                        value = senha,
                        onValueChange = {
                            senha = it
                        },

                        modifier = Modifier.fillMaxWidth(),

                        label = {
                            Text("Senha")
                        },

                        leadingIcon = {

                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null
                            )
                        },

                        shape = RoundedCornerShape(18.dp)
                    )

                    if (erro.isNotEmpty()) {

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = erro,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Button(

                        onClick = {

                            if (
                                usuario == "beatriz" &&
                                senha == "1234"
                            ) {

                                navController.navigate("dashboard") {

                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }

                            } else {

                                erro = "Usuário ou senha inválidos"
                            }
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),

                        shape = RoundedCornerShape(20.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE39A3B)
                        )
                    ) {

                        Text(
                            text = "ENTRAR",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationScreen() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {

            LoginScreen(navController)
        }

        composable("dashboard") {

            DashboardScreen(navController)
        }

        composable("categorias") {

            CategoriasPage()
        }

        composable("relatorios") {

            RelatoriosPage()
        }

        composable("movimentacoes") {

            MovimentacoesPage()
        }

        composable("notificacoes") {

            NotificacoesPage(navController)
        }

        composable("itens") {

            ItensPage(
                onOpenDetails = { item ->

                    navController.navigate(
                        "detalhesitens/${item.codigo}"
                    )
                }
            )
        }

        composable("detalhesitens/{codigo}") {

                backStackEntry ->

            val codigo =
                backStackEntry.arguments
                    ?.getString("codigo")

            val item =
                ItemRepository.itens.find {

                    it.codigo == codigo
                }

            item?.let {

                ItemDetalhesPage(
                    item = it,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

data class Notificacao(
    val titulo: String,
    val descricao: String
)

@Composable
fun NotificacoesPage(
    navController: NavHostController
) {

    val notificacoes = remember {

        mutableStateListOf(

            Notificacao(
                "Objeto transferido",
                "Notebook Dell foi transferido para a Sala 03"
            ),

            Notificacao(
                "Estado crítico",
                "2 computadores estão em estado ruim"
            ),

            Notificacao(
                "Novo item cadastrado",
                "Monitor LG UltraWide foi adicionado"
            ),

            Notificacao(
                "Manutenção necessária",
                "Impressora Epson precisa de manutenção"
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F3EE))
    ) {

        // TOPO

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE39A3B))
                    .clickable {
                        navController.popBackStack()
                    },

                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    text = "Notificações",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3E2723)
                )

                Text(
                    text = "${notificacoes.size} notificações pendentes",
                    color = Color.Gray
                )
            }
        }

        // SEM NOTIFICAÇÕES

        if (notificacoes.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF43A047),
                    modifier = Modifier.size(90.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Tudo em dia 🎉",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3E2723)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Você já leu todas as notificações.",
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

        } else {

            // LISTA DE NOTIFICAÇÕES

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF7F3EE))
                    .padding(horizontal = 16.dp),

                verticalArrangement = Arrangement.spacedBy(18.dp),

                contentPadding = PaddingValues(
                    top = 20.dp,
                    bottom = 120.dp
                )
            ){

                items(notificacoes) { notificacao ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                // REMOVE AO CLICAR
                                notificacoes.remove(notificacao)
                            },

                        shape = RoundedCornerShape(24.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),

                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ) {

                        Row(
                            modifier = Modifier.padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFFFF3E0)),

                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = null,
                                    tint = Color(0xFFE39A3B),
                                    modifier = Modifier.size(30.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(18.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = notificacao.titulo,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF3E2723)
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = notificacao.descricao,
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Toque para marcar como lida",
                                    color = Color(0xFFE39A3B),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardScreen(
    navController: NavHostController
) {

    var selectedMenu by remember {
        mutableStateOf("Dashboard")
    }

    val menuItems = listOf(
        Pair(Icons.Default.Home, "Dashboard"),
        Pair(Icons.Default.Menu, "Categorias"),
        Pair(Icons.Default.Info, "Relatórios"),
        Pair(Icons.Default.Inventory2, "Movimentações"),
        Pair(Icons.Default.Devices, "Itens"),
    )

    val itens = ItemRepository.itens

    val totalItens = itens.size

    val itensBom = itens.count {
        it.estado.equals("Bom", true)
    }

    val itensRegular = itens.count {
        it.estado.equals("Regular", true)
    }

    val itensRuim = itens.count {
        it.estado.equals("Ruim", true)
    }

    val porcentagemBom =
        if (totalItens > 0)
            itensBom.toFloat() / totalItens
        else 0f

    val porcentagemRegular =
        if (totalItens > 0)
            itensRegular.toFloat() / totalItens
        else 0f

    val porcentagemRuim =
        if (totalItens > 0)
            itensRuim.toFloat() / totalItens
        else 0f

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F3EE))
            .padding(horizontal = 16.dp),

        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 120.dp
        )
    ) {

        item {

            // TOPO

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Olá, Beatriz 👋",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF3E2723)
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text =
                            "Controle inteligente do inventário",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                // BOTÃO NOTIFICAÇÕES

                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE39A3B))
                        .clickable {

                            navController.navigate(
                                "notificacoes"
                            )
                        },

                    contentAlignment =
                        Alignment.Center
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Notifications,

                        contentDescription = null,

                        tint = Color.White,

                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // BANNER

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFFFFD180),
                                Color(0xFFFFB74D),
                                Color(0xFFE65100)
                            )
                        )
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),

                    verticalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Column {

                        Text(
                            text = "INVENTÁRIO",
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )

                        Text(
                            text = "DE ESCRITÓRIO",
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Text(
                            text =
                                "Gerencie equipamentos, móveis e movimentações.",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }

                    Button(
                        onClick = {
                            navController.navigate("itens")
                        },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),

                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Text(
                            text = "Ver Itens",
                            color = Color(0xFFE65100),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // MENU

            LazyRow(
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(menuItems) { item ->

                    MenuButton(
                        icon = item.first,
                        title = item.second,

                        selected =
                            selectedMenu == item.second
                    ) {

                        selectedMenu = item.second

                        when (item.second) {

                            "Dashboard" -> {
                                navController.navigate(
                                    "dashboard"
                                )
                            }

                            "Categorias" -> {
                                navController.navigate(
                                    "categorias"
                                )
                            }

                            "Relatórios" -> {
                                navController.navigate(
                                    "relatorios"
                                )
                            }

                            "Movimentações" -> {
                                navController.navigate(
                                    "movimentacoes"
                                )
                            }

                            "Itens" -> {
                                navController.navigate(
                                    "itens"
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // CARDS

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(14.dp),

                modifier = Modifier.fillMaxWidth()
            ) {

                InfoCard(
                    modifier = Modifier.weight(1f),

                    title = "Itens",

                    value = "$totalItens",

                    subtitle = "Cadastrados",

                    icon = Icons.Default.Inventory2,

                    iconColor = Color(0xFFE39A3B)
                )

                InfoCard(
                    modifier = Modifier.weight(1f),

                    title = "Categorias",

                    value =
                        "${CategoriaRepository.categorias.size}",

                    subtitle = "Ativas",

                    icon = Icons.Default.Menu,

                    iconColor = Color(0xFF8E24AA)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(14.dp),

                modifier = Modifier.fillMaxWidth()
            ) {

                InfoCard(
                    modifier = Modifier.weight(1f),

                    title = "Bom Estado",

                    value = "$itensBom",

                    subtitle = "Itens funcionando",

                    icon = Icons.Default.CheckCircle,

                    iconColor = Color(0xFF43A047)
                )

                InfoCard(
                    modifier = Modifier.weight(1f),

                    title = "Problemas",

                    value =
                        "${itensRegular + itensRuim}",

                    subtitle = "Precisam atenção",

                    icon = Icons.Default.Warning,

                    iconColor = Color(0xFFE53935)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ESTADO DOS EQUIPAMENTOS

            Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(28.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Text(
                        text =
                            "Estado dos Equipamentos",

                        fontWeight = FontWeight.Bold,

                        fontSize = 20.sp,

                        color = Color(0xFF3E2723)
                    )

                    Spacer(
                        modifier = Modifier.height(22.dp)
                    )

                    ProgressItem(
                        label = "Bom",

                        value =
                            "$itensBom itens",

                        progress = porcentagemBom,

                        color = Color(0xFF43A047)
                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    ProgressItem(
                        label = "Regular",

                        value =
                            "$itensRegular itens",

                        progress =
                            porcentagemRegular,

                        color = Color(0xFFFFB300)
                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    ProgressItem(
                        label = "Ruim",

                        value =
                            "$itensRuim itens",

                        progress =
                            porcentagemRuim,

                        color = Color(0xFFE53935)
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

data class Categoria(
    val nome: String,
    val descricao: String,
    val quantidade: Int,
    val icon: ImageVector
)
object CategoriaRepository {

    val categorias = mutableStateListOf(

        Categoria(
            "Mobiliário",
            "Mesas, cadeiras, armários e gaveteiros",
            45,
            Icons.Default.Inventory2
        ),

        Categoria(
            "Eletrônicos",
            "Impressoras, aparelhos e projetores",
            32,
            Icons.Default.Devices
        ),

        Categoria(
            "Informática",
            "Computadores, monitores e teclados",
            28,
            Icons.Default.Computer
        )
    )
}

@Composable
fun CategoriasPage() {

    val categorias = CategoriaRepository.categorias

    val corPrincipal = Color(0xFFE39A3B)

    var abrirDialog by remember {
        mutableStateOf(false)
    }

    var indexEditando by remember {
        mutableIntStateOf(-1)
    }

    var nome by remember {
        mutableStateOf("")
    }

    var descricao by remember {
        mutableStateOf("")
    }

    if (abrirDialog) {

        AlertDialog(

            onDismissRequest = {
                abrirDialog = false
            },

            title = {

                Text(
                    text =
                        if (indexEditando == -1)
                            "Nova Categoria"
                        else
                            "Editar Categoria",

                    fontWeight = FontWeight.Bold
                )
            },

            text = {

                Column {

                    OutlinedTextField(
                        value = nome,
                        onValueChange = {
                            nome = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Nome da categoria")
                        },
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = descricao,
                        onValueChange = {
                            descricao = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Descrição")
                        },
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            },

            confirmButton = {

                Button(

                    colors = ButtonDefaults.buttonColors(
                        containerColor = corPrincipal
                    ),

                    shape = RoundedCornerShape(14.dp),

                    onClick = {

                        if (
                            nome.isNotBlank() &&
                            descricao.isNotBlank()
                        ) {

                            if (indexEditando == -1) {

                                categorias.add(

                                    Categoria(
                                        nome,
                                        descricao,
                                        0,
                                        Icons.Default.Category
                                    )
                                )

                            } else {

                                categorias[indexEditando] =

                                    Categoria(
                                        nome,
                                        descricao,
                                        categorias[indexEditando].quantidade,
                                        categorias[indexEditando].icon
                                    )
                            }

                            abrirDialog = false

                            nome = ""
                            descricao = ""

                            indexEditando = -1
                        }
                    }
                ) {

                    Text("Salvar")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        abrirDialog = false
                    }
                ) {

                    Text("Cancelar")
                }
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFFFFCF8),
                        Color(0xFFF7F3EE)
                    )
                )
            )
            .padding(horizontal = 16.dp),

        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 120.dp
        )
    ) {

        item {

            Text(
                text = "Categorias",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E2723)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Gerencie as categorias do inventário",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    nome = ""
                    descricao = ""

                    indexEditando = -1

                    abrirDialog = true
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = corPrincipal
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Nova Categoria",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp),

                modifier = Modifier.fillMaxWidth()
            ) {

                RelatorioCard(
                    modifier = Modifier.weight(1f),
                    titulo = "Categorias",
                    valor = categorias.size.toString(),
                    icon = Icons.Default.Category,
                    iconColor = Color(0xFFE39A3B)
                )

                RelatorioCard(
                    modifier = Modifier.weight(1f),
                    titulo = "Itens",
                    valor = ItemRepository.itens.sumOf {
                        it.quantidade
                    }.toString(),
                    icon = Icons.Default.Inventory2,
                    iconColor = Color(0xFF4CAF50)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))
        }

        itemsIndexed(categorias) { index, categoria ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),

                shape = RoundedCornerShape(22.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(CircleShape)
                                .background(
                                    corPrincipal.copy(alpha = 0.15f)
                                ),

                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = categoria.icon,
                                contentDescription = null,
                                tint = corPrincipal
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(14.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = categoria.nome,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text = categoria.descricao,
                                color = Color.Gray,
                                fontSize = 13.sp
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement =
                            Arrangement.SpaceBetween,
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Color(0xFFFFF3E0)
                                )
                                .padding(
                                    horizontal = 12.dp,
                                    vertical = 6.dp
                                )
                        ) {

                            Text(
                                text = "${categoria.quantidade} itens",
                                color = Color(0xFFE65100),
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(
                            horizontalArrangement =
                                Arrangement.spacedBy(8.dp)
                        ) {

                            Button(
                                onClick = {

                                    indexEditando = index
                                    nome = categoria.nome
                                    descricao = categoria.descricao

                                    abrirDialog = true
                                },

                                shape = RoundedCornerShape(12.dp),

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF81C784)
                                )
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null
                                )
                            }

                            Button(
                                onClick = {
                                    categorias.removeAt(index)
                                },

                                shape = RoundedCornerShape(12.dp),

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFE57373)
                                )
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RelatoriosPage() {

    val itens = ItemRepository.itens

    val categorias = itens.map {
        it.categoria
    }.distinct()

    var categoriaSelecionada by remember {
        mutableStateOf("Todas")
    }

    var relatorioGerado by remember {
        mutableStateOf(false)
    }

    val itensFiltrados = if (
        categoriaSelecionada == "Todas"
    ) {
        itens
    } else {
        itens.filter {
            it.categoria == categoriaSelecionada
        }
    }

    val totalItens = itensFiltrados.size

    val itensBom = itensFiltrados.count {
        it.estado == "Bom"
    }

    val itensRegular = itensFiltrados.count {
        it.estado == "Regular"
    }

    val itensRuim = itensFiltrados.count {
        it.estado == "Ruim"
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFFFFCF8),
                        Color(0xFFF7F3EE)
                    )
                )
            )
            .padding(horizontal = 16.dp),

        verticalArrangement = Arrangement.spacedBy(14.dp),

        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 120.dp
        )
    ){

        item {

            Text(
                text = "Relatórios",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E2723)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Visualize estatísticas reais do inventário",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyRow(
                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                item {

                    CategoriaFiltro(
                        titulo = "Todas",
                        selecionado =
                            categoriaSelecionada == "Todas"
                    ) {

                        categoriaSelecionada = "Todas"
                    }
                }

                items(categorias) { categoria ->

                    CategoriaFiltro(
                        titulo = categoria,
                        selecionado =
                            categoriaSelecionada == categoria
                    ) {

                        categoriaSelecionada = categoria
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    relatorioGerado = true
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE39A3B)
                )
            ) {

                Text(
                    text = "Gerar Relatório Inteligente",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (relatorioGerado) {

                // CARDS

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    RelatorioCard(
                        modifier = Modifier.weight(1f),
                        titulo = "Itens",
                        valor = totalItens.toString(),
                        icon = Icons.Default.Inventory2,
                        iconColor = Color(0xFFE39A3B)
                    )

                    RelatorioCard(
                        modifier = Modifier.weight(1f),
                        titulo = "Bom",
                        valor = itensBom.toString(),
                        icon = Icons.Default.CheckCircle,
                        iconColor = Color(0xFF4CAF50)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    RelatorioCard(
                        modifier = Modifier.weight(1f),
                        titulo = "Regular",
                        valor = itensRegular.toString(),
                        icon = Icons.Default.Warning,
                        iconColor = Color(0xFFFF9800)
                    )

                    RelatorioCard(
                        modifier = Modifier.weight(1f),
                        titulo = "Ruim",
                        valor = itensRuim.toString(),
                        icon = Icons.Default.Delete,
                        iconColor = Color(0xFFE53935)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // STATUS

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "Estado do Inventário",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )

                        ProgressItem(
                            label = "Bom",
                            value = "$itensBom itens",
                            progress =
                                if (totalItens > 0)
                                    itensBom.toFloat() / totalItens
                                else
                                    0f,
                            color = Color(0xFF4CAF50)
                        )

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        ProgressItem(
                            label = "Regular",
                            value = "$itensRegular itens",
                            progress =
                                if (totalItens > 0)
                                    itensRegular.toFloat() / totalItens
                                else
                                    0f,
                            color = Color(0xFFFF9800)
                        )

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        ProgressItem(
                            label = "Ruim",
                            value = "$itensRuim itens",
                            progress =
                                if (totalItens > 0)
                                    itensRuim.toFloat() / totalItens
                                else
                                    0f,
                            color = Color(0xFFE53935)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // LISTA DOS ITENS

                Text(
                    text = "Itens Encontrados",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                itensFiltrados.forEach { item ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 18.dp),

                        shape = RoundedCornerShape(22.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),

                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ) {

                        Row(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Image(
                                painter = painterResource(id = item.imagem),
                                contentDescription = item.nome,

                                modifier = Modifier
                                    .size(85.dp)
                                    .clip(RoundedCornerShape(16.dp)),

                                contentScale = ContentScale.Crop
                            )

                            Spacer(
                                modifier = Modifier.width(16.dp)
                            )

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = item.nome,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )

                                Text(
                                    text = "Código: ${item.codigo}",
                                    color = Color.Gray,
                                    fontSize = 13.sp
                                )

                                Text(
                                    text = "Categoria: ${item.categoria}",
                                    color = Color.DarkGray,
                                    fontSize = 13.sp
                                )

                                Text(
                                    text = "Local: ${item.localizacao}",
                                    color = Color.DarkGray,
                                    fontSize = 13.sp
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                val corEstado = when (item.estado) {

                                    "Bom" ->
                                        Color(0xFF4CAF50)

                                    "Regular" ->
                                        Color(0xFFFF9800)

                                    else ->
                                        Color(0xFFE53935)
                                }

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(
                                            corEstado.copy(alpha = 0.15f)
                                        )
                                        .padding(
                                            horizontal = 10.dp,
                                            vertical = 6.dp
                                        )
                                ) {

                                    Text(
                                        text = item.estado,
                                        color = corEstado,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RelatorioCampo(
    titulo: String,
    valor: String,
    onClick: () -> Unit
) {

    Column {

        Text(
            text = titulo,
            color = Color.Gray,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(
                    1.dp,
                    Color(0xFFE8DED3),
                    RoundedCornerShape(16.dp)
                )
                .clickable { onClick() }
                .padding(18.dp),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(text = valor)

            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun RelatorioCard(
    modifier: Modifier = Modifier,
    titulo: String,
    valor: String,
    icon: ImageVector,
    iconColor: Color = Color(0xFFFFB300)
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = titulo,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = valor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

data class Movimentacao(
    val data: String,
    val tipo: String,
    val item: String,
    val quantidade: Int,
    val descricao: String,
    val usuario: String
)

object MovimentacaoRepository {

    val movimentacoes = mutableStateListOf(
        Movimentacao(
            "10/01/2024",
            "Entrada",
            "Computador Desktop",
            5,
            "Compra de novos computadores",
            "Beatriz Bateta"
        ),
        Movimentacao(
            "15/01/2024",
            "Transferência",
            "Impressora Laser",
            1,
            "Transferido para sala 02",
            "Beatriz Bateta"
        ),
        Movimentacao(
            "20/01/2024",
            "Saída",
            "Cadeira de Escritório",
            2,
            "Itens danificados",
            "Beatriz Bateta"
        ),
        Movimentacao(
            "25/01/2024",
            "Entrada",
            "Armário Arquivo",
            1,
            "Compra de novo armário",
            "Beatriz Bateta"
        )
    )
}

@Composable
fun MovimentacoesPage() {

    val movimentacoes = MovimentacaoRepository.movimentacoes

    var filtroTipo by remember { mutableStateOf("Todos") }
    var filtroTexto by remember { mutableStateOf("") }
    var filtroData by remember { mutableStateOf("") }

    val tipos = listOf("Todos", "Entrada", "Saída", "Transferência")

    val listaFiltrada = movimentacoes.filter { mov ->

        val tipoOk = filtroTipo == "Todos" || mov.tipo == filtroTipo

        val textoOk =
            mov.item.contains(filtroTexto, ignoreCase = true)

        val dataOk =
            filtroData.isBlank() || mov.data.contains(filtroData)

        tipoOk && textoOk && dataOk
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F1EC))
    ) {

        // TOPO
        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = "Movimentações",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E2723)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Filtre entradas e saídas do inventário",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // FILTRO POR TIPO
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(tipos) { tipo ->

                    val selected = filtroTipo == tipo

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                if (selected) Color(0xFFFFB74D)
                                else Color.White
                            )
                            .clickable {
                                filtroTipo = tipo
                            }
                            .padding(horizontal = 14.dp, vertical = 10.dp)
                    ) {

                        Text(
                            text = tipo,
                            color = if (selected) Color.White else Color.DarkGray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // BUSCA POR ITEM
            OutlinedTextField(
                value = filtroTexto,
                onValueChange = { filtroTexto = it },
                label = { Text("Buscar item") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            // FILTRO POR DATA
            OutlinedTextField(
                value = filtroData,
                onValueChange = { filtroData = it },
                label = { Text("Filtrar por data (ex: 10/01/2024)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        // LISTA
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F3EE))
                .padding(horizontal = 16.dp),

            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 120.dp
            )
        ) {

            items(listaFiltrada) { mov ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp),

                    shape = RoundedCornerShape(22.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column {

                                Text(
                                    text = mov.item,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )

                                Text(
                                    text = mov.data,
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }

                            val cor = when (mov.tipo) {
                                "Entrada" -> Color(0xFF4CAF50)
                                "Saída" -> Color(0xFFE53935)
                                else -> Color(0xFF2196F3)
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(cor.copy(alpha = 0.15f))
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = mov.tipo,
                                    color = cor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = mov.descricao,
                            fontSize = 13.sp,
                            color = Color.DarkGray
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Qtd: ${mov.quantidade}",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

data class Item(
    val codigo: String,
    val nome: String,
    val categoria: String,
    val localizacao: String,
    val estado: String,
    val quantidade: Int,
    val imagem: Int
)

object ItemRepository {

    val itens = mutableStateListOf(

        Item(
            "MOB001",
            "Cadeira Ergonômica",
            "Mobiliário",
            "Sala 01",
            "Bom",
            15,
            R.drawable.cadeira
        ),

        Item(
            "MOB002",
            "Mesa Escritório",
            "Mobiliário",
            "Sala 02",
            "Bom",
            12,
            R.drawable.mesa
        ),

        Item(
            "MOB003",
            "Armário Arquivo",
            "Mobiliário",
            "Arquivo",
            "Regular",
            3,
            R.drawable.armario
        ),

        Item(
            "ELE001",
            "Impressora Laser",
            "Eletrônicos",
            "Recepção",
            "Bom",
            2,
            R.drawable.impressora
        ),

        Item(
            "INF001",
            "Computador Desktop",
            "Informática",
            "Sala 01",
            "Bom",
            10,
            R.drawable.computador
        )
    )
}

@Composable
fun ItensPage(
    onOpenDetails: (Item) -> Unit
) {

    val itens = ItemRepository.itens

    var busca by remember { mutableStateOf("") }

    var abrirDialog by remember { mutableStateOf(false) }

    var codigo by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var localizacao by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("Bom") }
    var quantidade by remember { mutableStateOf("") }

    val corPadrao = Color(0xFFE39A3B)

    val filtrados = itens.filter {
        it.nome.contains(busca, ignoreCase = true) ||
                it.codigo.contains(busca, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F1EC))
            .padding(16.dp)
    ) {

        Text(
            text = "Itens",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Gerencie todos os itens cadastrados",
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = busca,
            onValueChange = { busca = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Buscar item ou código") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                abrirDialog = true
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            shape = RoundedCornerShape(18.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = corPadrao
            )
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Novo Item",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F3EE)),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 120.dp
            )
        ) {

            items(filtrados) { item ->

                ItemCard(item) {
                    onOpenDetails(item)
                }
            }
        }
    }

    if (abrirDialog) {

        AlertDialog(

            onDismissRequest = {
                abrirDialog = false
            },

            title = {
                Text("Novo Item")
            },

            text = {

                Column {

                    OutlinedTextField(
                        value = codigo,
                        onValueChange = { codigo = it },
                        label = { Text("Código") }
                    )

                    OutlinedTextField(
                        value = nome,
                        onValueChange = { nome = it },
                        label = { Text("Nome") }
                    )

                    OutlinedTextField(
                        value = categoria,
                        onValueChange = { categoria = it },
                        label = { Text("Categoria") }
                    )

                    OutlinedTextField(
                        value = localizacao,
                        onValueChange = { localizacao = it },
                        label = { Text("Localização") }
                    )

                    OutlinedTextField(
                        value = quantidade,
                        onValueChange = { quantidade = it },
                        label = { Text("Quantidade") }
                    )
                }
            },

            confirmButton = {

                Button(

                    colors = ButtonDefaults.buttonColors(
                        containerColor = corPadrao
                    ),

                    onClick = {

                        if (
                            codigo.isNotBlank() &&
                            nome.isNotBlank()
                        ) {

                            ItemRepository.itens.add(

                                Item(
                                    codigo = codigo,
                                    nome = nome,
                                    categoria = categoria,
                                    localizacao = localizacao,
                                    estado = estado,
                                    quantidade = quantidade.toIntOrNull() ?: 0,

                                    // imagem padrão
                                    imagem = R.drawable.caixa
                                )
                            )

                            codigo = ""
                            nome = ""
                            categoria = ""
                            localizacao = ""
                            quantidade = ""

                            abrirDialog = false
                        }
                    }
                ) {

                    Text("Salvar")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        abrirDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun ItemCard(
    item: Item,
    onClick: () -> Unit
) {

    val estadoColor = when (item.estado) {

        "Bom" -> Color(0xFF4CAF50)

        "Regular" -> Color(0xFFFF9800)

        else -> Color(0xFFE53935)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp)
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = item.imagem),
                contentDescription = item.nome,

                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(16.dp)),

                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Column {

                        Text(
                            text = item.codigo,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Text(
                            text = item.nome,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                estadoColor.copy(alpha = 0.15f)
                            )
                            .padding(
                                horizontal = 10.dp,
                                vertical = 6.dp
                            )
                    ) {

                        Text(
                            text = item.estado,
                            color = estadoColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Categoria: ${item.categoria}",
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )

                Text(
                    text = "Local: ${item.localizacao}",
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Qtd: ${item.quantidade}",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun ItemDetalhesPage(
    item: Item,
    onBack: () -> Unit
) {

    var abrirDialog by remember {
        mutableStateOf(false)
    }

    var nome by remember {
        mutableStateOf(item.nome)
    }

    var categoria by remember {
        mutableStateOf(item.categoria)
    }

    var localizacao by remember {
        mutableStateOf(item.localizacao)
    }

    var estado by remember {
        mutableStateOf(item.estado)
    }

    var quantidade by remember {
        mutableStateOf(item.quantidade.toString())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F1EC))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(Color(0xFFFFD180))
        ) {

            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable {

                        onBack()
                    },

                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color(0xFFE39A3B)
                )
            }

            // IMAGEM DO ITEM
            Image(
                painter = painterResource(id = item.imagem),
                contentDescription = item.nome,

                modifier = Modifier
                    .align(Alignment.Center)
                    .size(130.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color.White),

                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = item.nome,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E2723)
            )

            Text(
                text = item.codigo,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            InfoRow(
                label = "Categoria",
                value = item.categoria
            )

            InfoRow(
                label = "Localização",
                value = item.localizacao
            )

            InfoRow(
                label = "Estado",
                value = item.estado
            )

            InfoRow(
                label = "Quantidade",
                value = item.quantidade.toString()
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Button(
                    onClick = {
                        onBack()
                    },

                    modifier = Modifier.weight(1f),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE39A3B)
                    )
                ) {

                    Text("Voltar")
                }

                Button(
                    onClick = {

                        abrirDialog = true
                    },

                    modifier = Modifier.weight(1f),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {

                    Text("Editar")
                }
            }
        }
    }

    // DIALOG EDITAR
    if (abrirDialog) {

        AlertDialog(

            onDismissRequest = {
                abrirDialog = false
            },

            title = {
                Text("Editar Item")
            },

            text = {

                Column {

                    OutlinedTextField(
                        value = nome,
                        onValueChange = {
                            nome = it
                        },
                        label = {
                            Text("Nome")
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = categoria,
                        onValueChange = {
                            categoria = it
                        },
                        label = {
                            Text("Categoria")
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = localizacao,
                        onValueChange = {
                            localizacao = it
                        },
                        label = {
                            Text("Localização")
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = estado,
                        onValueChange = {
                            estado = it
                        },
                        label = {
                            Text("Estado")
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = quantidade,
                        onValueChange = {
                            quantidade = it
                        },
                        label = {
                            Text("Quantidade")
                        }
                    )
                }
            },

            confirmButton = {

                Button(

                    onClick = {

                        val index =
                            ItemRepository.itens.indexOf(item)

                        if (index != -1) {

                            ItemRepository.itens[index] =

                                Item(
                                    codigo = item.codigo,
                                    nome = nome,
                                    categoria = categoria,
                                    localizacao = localizacao,
                                    estado = estado,
                                    quantidade = quantidade.toIntOrNull() ?: 0,
                                    imagem = item.imagem
                                )
                        }

                        abrirDialog = false
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF81C784)
                    )
                ) {

                    Text("Salvar")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        abrirDialog = false
                    }
                ) {

                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun InfoRow(
    label: String,
    value: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Text(
                text = label,
                color = Color.Gray,
                fontSize = 13.sp
            )

            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E2723)
            )
        }
    }
}

@Composable
fun MenuButton(
    icon: ImageVector,
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Column(
        horizontalAlignment =
            Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (selected)
                    Color(0xFFE39A3B)
                else
                    Color.White
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 18.dp,
                vertical = 14.dp
            )
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint =
                if (selected)
                    Color.White
                else
                    Color.DarkGray,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = title,
            fontSize = 12.sp,
            color =
                if (selected)
                    Color.White
                else
                    Color.DarkGray
        )
    }
}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    subtitle: String,
    icon: ImageVector,
    iconColor: Color
) {

    val context = LocalContext.current

    Card(
        modifier = modifier
            .height(150.dp)
            .clickable {

                Toast.makeText(
                    context,
                    "$title selecionado",
                    Toast.LENGTH_SHORT
                ).show()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement =
                Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(
                        iconColor.copy(alpha = 0.15f)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor
                )
            }

            Column {

                Text(
                    text = value,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ProgressItem(
    label: String,
    value: String,
    progress: Float,
    color: Color
) {

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = value,
                fontSize = 13.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(20.dp)),
            color = color,
            trackColor = Color(0xFFECECEC)
        )
    }
}

@Composable
fun CategoriaFiltro(
    titulo: String,
    selecionado: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (selecionado)
                    Color(0xFFE39A3B)
                else
                    Color.White
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp
            )
    ) {

        Text(
            text = titulo,
            color =
                if (selecionado)
                    Color.White
                else
                    Color.Black
        )
    }
};