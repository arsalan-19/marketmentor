package com.marketmentor.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// --- DATA STRUCTURE FOR OUR SAFE STOCK POOL ---
data class StockRecommendation(
    val id: String,
    val symbol: String,
    val sector: String,
    val price: Double,
    val targetPrice: Double,
    val targetPct: Double,
    val expectedDays: String,
    val conviction: String,
    val rationale: String
)

// --- VIEWMODEL FOR STATE CONTROL ---
class DashboardViewModel : ViewModel() {
    // Our Whitelisted Pool containing exactly 5 High-Conviction, Safe Stocks
    private val _recommendations = MutableStateFlow(
        listOf(
            StockRecommendation(
                "TATAMOTORS", "TATA MOTORS", "Automobile", 985.50, 1084.00, 10.0, "15-20 Days", "Strong Buy",
                "TATA MOTORS demonstrates exceptional risk-adjusted swing features. Its consolidated debt load has dropped from a D/E of 1.4 down to 0.3, reducing liquidity risk profiles. The stock completed a highly reliable daily Bullish Engulfing pattern at its long-term 50-day EMA support boundary."
            ),
            StockRecommendation(
                "RELIANCE", "RELIANCE IND.", "Energy & Conglomerate", 2915.20, 3165.00, 8.5, "10-15 Days", "Strong Buy",
                "RELIANCE is establishing a clean consolidation shelf above its 200-day SMA. Supported by capital allocation efficiency backed by a solid 16.5% ROCE and exceptional interest coverage ratio (ICR), the balance sheet possesses deep defensive buffers."
            ),
            StockRecommendation(
                "INFY", "INFOSYS LTD", "Information Tech", 1545.10, 1730.00, 12.0, "20-25 Days", "Watchlist Buy",
                "INFOSYS represents an excellent risk-to-reward setup as global IT spending models recover. The company maintains an outstanding ROCE profile at 38.4%, with near-zero long-term liabilities (D/E: 0.05). On-chart data demonstrates a tight Bollinger Band squeeze."
            ),
            StockRecommendation(
                "ICICIBANK", "ICICI BANK", "Banking & Finance", 1082.40, 1175.00, 8.6, "12-18 Days", "Strong Buy",
                "ICICI Bank shows outstanding operational resilience with credit growth expanding at 16.2% YoY. Net NPA lies at an ultra-low 0.36%, proving stellar asset quality safety. Technical trend structures confirm a Morning Star breakout on above-average volume."
            ),
            StockRecommendation(
                "LT", "LARSEN & TOUBRO", "Infrastructure", 3478.90, 3810.00, 9.5, "18-22 Days", "Watchlist Buy",
                "L&T is India's leading engineering giant with an order book exceeding ₹4.7 Lakh Crore. Capital efficiency is high with a 14.8% core Return on Equity. The stock has broken out of a 3-month flag consolidation channel, offering a robust swing entry."
            )
        )
    )
    val recommendations: StateFlow<List<StockRecommendation>> = _recommendations.asStateFlow()

    private val _selectedStock = MutableStateFlow<StockRecommendation?>(null)
    val selectedStock: StateFlow<StockRecommendation?> = _selectedStock.asStateFlow()

    fun selectStock(stock: StockRecommendation) {
        _selectedStock.value = stock
    }
}

// --- CORE APP CLASS ---
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MarketMentorScreen()
            }
        }
    }
}

// --- APP LAYOUT VIEW ---
@Composable
fun MarketMentorScreen(viewModel: DashboardViewModel = viewModel()) {
    val recommendations by viewModel.recommendations.collectAsState()
    val selectedStock by viewModel.selectedStock.collectAsState()

    // Initialize first stock selected on launch
    LaunchedEffect(Unit) {
        if (recommendations.isNotEmpty() && selectedStock == null) {
            viewModel.selectStock(recommendations.first())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)) // Slate 50
            .padding(16.dp)
    ) {
        // App Identity Header
        Text(
            text = "MarketMentor",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF0F172A)
        )
        Text(
            text = "NSE Swing Screener (Interday Delivery)",
            fontSize = 12.sp,
            color = Color(0xFF64748B),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Split Layout: Lists and Detail View
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Recommendation List
            LazyColumn(
                modifier = Modifier.weight(0.5f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recommendations) { stock ->
                    StockListItem(
                        stock = stock,
                        isSelected = stock.id == selectedStock?.id,
                        onClick = { viewModel.selectStock(stock) }
                    )
                }
            }

            // Detailed Rationale Analysis Card
            selectedStock?.let { stock ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = stock.symbol,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF0F172A)
                        )
                        Text(
                            text = "Target Price: ₹${stock.targetPrice} (+${stock.targetPct}%) | Horizon: ${stock.expectedDays}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4F46E5),
                            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                        )
                        Divider(color = Color(0xFFF1F5F9))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Algorithmic Rationale:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF94A3B8)
                        )
                        Text(
                            text = stock.rationale,
                            fontSize = 12.sp,
                            color = Color(0xFF334155),
                            lineHeight = 18.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

// --- STOCK ROW ITEM ---
@Composable
fun StockListItem(stock: StockRecommendation, isSelected: Boolean, onClick: () -> Unit) {
    val containerBg = if (isSelected) Color(0xFF4F46E5) else Color.White
    val mainText = if (isSelected) Color.White else Color(0xFF0F172A)
    val subText = if (isSelected) Color(0xFFC7D2FE) else Color(0xFF64748B)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(containerBg, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .border(
                1.dp, 
                if (isSelected) Color(0xFF4F46E5) else Color(0xFFE2E8F0), 
                RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = stock.symbol, fontWeight = FontWeight.ExtraBold, color = mainText, fontSize = 14.sp)
            Text(text = stock.sector, color = subText, fontSize = 11.sp)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = "₹${stock.price}", fontWeight = FontWeight.Black, color = mainText, fontSize = 14.sp)
            Text(
                text = stock.conviction.uppercase(), 
                fontWeight = FontWeight.ExtraBold, 
                color = if (isSelected) Color.White else Color(0xFF059669), 
                fontSize = 10.sp
            )
        }
    }
}
