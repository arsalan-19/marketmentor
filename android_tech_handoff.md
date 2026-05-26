🤖 Android Technical Handoff Guide: MarketMentor

This document serves as the architectural handoff blueprint for Android engineers transitioning MarketMentor from our web prototype into a native, high-performance Android application built with Kotlin and Jetpack Compose.

📁 1. Target Android Directory Tree

To maintain corporate code hygiene and support seamless dependency injection, the Android application codebase must follow this standard clean architecture package structure:

<img width="955" height="655" alt="image" src="https://github.com/user-attachments/assets/5f4bf5c0-3a60-48b4-adfd-febb3e8eb120" />


☕ 2. Key Kotlin Data Classes & State Schemas

2.1 The Positional Swing Stock Schema

This class models the stock recommendation payload, integrating calculated target prices, estimated horizons, and multi-factor indicator states:
'''
package com.marketmentor.app.domain.model

import java.util.UUID

data class Stock(
    val id: String = UUID.randomUUID().toString(),
    val symbol: String,               // e.g., "TATA MOTORS"
    val code: String,                 // e.g., "TATAMOTORS"
    val sector: String,               // e.g., "Automobile"
    val price: Double,                // Current Market Price (CMP)
    val targetPrice: Double,          // Calculated Target (e.g., CMP + 10%)
    val targetPct: Double,            // Target percentage (e.g., 10.0)
    val expectedDays: String,         // e.g., "15 - 20"
    val score: Int,                   // Pre-calculated Confidence Score (0-100)
    val conviction: ConvictionType,   // STRONG_BUY, BUY, WATCHLIST_BUY, AVOID
    val rationale: String,            // NLG Synthesized plain-English rationale
    val fundamentals: List<Metric>,   // Pre-calculated financial ratios
    val technicals: List<Metric>,     // Client-side processed momentum metrics
    val candles: List<Metric>         // Client-side processed pattern recognitions
)

enum class ConvictionType {
    STRONG_BUY, BUY, WATCHLIST_BUY, AVOID
}

data class Metric(
    val name: String,
    val value: String,
    val status: String,
    val explanation: String? = null,
    val code: String? = null          // Linked to Jargon Database for tooltips
)
'''

2.2 The Positional Delivery Holding Schema

Models the active, non-intraday holdings, incorporating strict Same-Day ($T+0$) settlement locks to block speculation:

package com.marketmentor.app.domain.model

import java.util.Date

data class Holding(
    val id: String = UUID.randomUUID().toString(),
    val code: String,                 // Ticker symbol code
    val name: String,                 // Corporate brand name
    val qty: Int,                     // Position size quantity
    val buyPrice: Double,             // Average purchase price
    val targetPrice: Double,          // Expected exit price
    val purchaseDay: Int,             // Simulated day of purchase
    val purchaseTimestamp: Date = Date(), // System timestamp
    val isSettled: Boolean = false    // Set to TRUE only upon advancing simulated session (T+1)
)


🎨 3. Jetpack Compose UI Components Architecture

3.1 Capital Protection Guardrail Interlock (Kotlin)

This standard Material 3 Dialog composable blocks same-day sales. This replaces standard alert modals to reinforce our strict delivery boundaries:

package com.marketmentor.app.presentation.portfolio.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.marketmentor.app.presentation.theme.PrimaryIndigo
import com.marketmentor.app.presentation.theme.RiskRose

@Composable
fun IntradayLockDialog(
    onDismiss: () -> Unit,
    onAdvanceToNextDay: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = "Intraday Liquidity Interlock", color = Color.Black)
        },
        text = {
            Text(
                text = "To protect retail capital from impulsive volatility, MarketMentor enforces a strict delivery-only framework.\n\n" +
                       "Under simulated T+1 settlement rules, assets purchased today are locked and cannot be sold " +
                       "during the same trading session. They are held overnight to settle."
            )
        },
        confirmButton = {
            Button(
                onClick = { onAdvanceToNextDay() },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
            ) {
                Text("Simulate Next Day (T+1)")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Keep Position", color = RiskRose)
            }
        }
    )
}


🛠️ 4. Local Execution & Testing Sandbox Strategies

To empirically test the Android application before deploying, developers must observe three key testing parameters:

State Retention: Virtual ledger states, cash balances, and trade histories must persist between application sessions using local Room caching or encrypted shared preferences (DataStore).

Mock Price Swings: The simulator must generate pseudorandom pricing adjustments bounded between $-3\%$ and $+4\%$ during session transitions to model realistic market volatility.

Handoff Completeness: Ensure that when Retrofit fetches live pricing, it maps values dynamically back to local SQLite databases, updating your calculated Target Price and Expected Horizons on-screen instantly.
