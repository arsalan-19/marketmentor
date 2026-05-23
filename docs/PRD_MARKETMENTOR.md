Product Requirements Document (PRD)

Product Name: MarketMentor (Working Title)

Document Version: 2.0.0

Target Role Portfolio: Senior Product Manager (FinTech / WealthTech)

Target Platform: Android (Native Kotlin/Compose MVP)

Document Status: Approved for Development

1. Executive Summary & Product Vision

1.1 Product Vision

MarketMentor is an intelligent, highly disciplined Android application engineered to democratize access to institutional-grade equity analysis for retail investors in the Indian Stock Market (NSE). By systematically automating the workflow of a veteran researcher, the platform bridges the gap between raw data and disciplined execution. It synthesizes comprehensive, public corporate filings with real-time technical momentum and candlestick patterns to generate high-conviction, swing/positional recommendations.

The core ethos of MarketMentor is educational validation: it provides a risk-free, virtual trading ledger to let users empirically verify the profitability of algorithmic recommendations before deploying real capital.

1.2 The Hard Boundary: Delivery/Interday Only

To protect retail capital and foster disciplined wealth creation, MarketMentor strictly prohibits intraday and Futures & Options (F&O) trading.

All recommendations, virtual trades, and tracking metrics are explicitly locked to delivery/interday transactions (positional swing trading with holding periods ranging from 2 days to several weeks/months).

By filtering out short-term speculative noise, high leverage, and complex option Greeks, the platform ensures the user's cognitive load is channeled purely into studying stable, high-value equity trends.

Capital settlement follows a strict simulated $T+1$ settlement schedule. Positions acquired during a trading day are locked and cannot be liquidated until the subsequent trading session.

2. Problem Statement & Market Context

2.1 The Retail Investor Challenge in India (NSE)

The Indian retail investing landscape has seen explosive growth, yet a majority of new retail accounts suffer consistent capital erosion. This is driven by three main factors:

Speculative Over-Leveraging: Over-exposure to high-risk, intraday scalping and highly complex $F\&O$ segments without fundamental or risk management frameworks.

Cognitive Overload & Asymmetric Information: Sifting through quarterly financial statements (Balance Sheets, Profit & Loss Statements, Cash Flows) and correlating them with real-time price action is mathematically and logistically impossible for a casual retail investor.

Lack of Trust & Transparency: Commercial stock-tipping channels lack accountability. Beginners are asked to trust recommendations blindly, with no analytical audit trail or safe sandbox to empirically test recommendations over time.

2.2 Objective

Develop a low-operating-cost, highly scalable, and exceptionally simple Android decision-support platform that acts as a disciplined, rule-based algorithmic research assistant. It must ingest massive quantities of fundamental and technical data, filter out high-risk entities, analyze candlestick structures, and present clean, explainable, and fully trackable positional trading suggestions on a daily and on-demand basis.

3. Target Audience & User Persona

Primary Persona: "The Cautious Beginner" (Siddharth, 26, Bengaluru)

Demographics: Young professional, IT Consultant, earning $₹12,00,00,000$ per annum.

Behavioral Profile: Intrigued by equity-market returns but highly risk-averse. Has opened a demat account but is paralyzed by fear of losing capital. Has no formal training in finance, accounting, or charts.

Goals: Wants to learn swing/positional investing systematically, verify stock recommendation performance safely via virtual money, and transition into an active, self-directed investor.

Core Pain Points:

Overwhelmed by financial terminology ($P/E$, $ROCE$, $MACD$ crossovers, Engulfing candles).

Prone to "fear of missing out" (FOMO) and emotional trading based on unverified social media tips.

Wants to know the concrete why behind a stock pick without spending hours dissecting complex balance sheets.

4. System Architecture & Dual-Engine Data Processing Flow

To optimize data pipeline costs while serving hyper-accurate, real-time insights, MarketMentor employs a Hybrid Data Processing Framework.

[ NSE / Public Financial API ]
             │
             ├──► (Every Morning / 08:30 IST) ──► [Daily Batch Processing Engine]
             │                                              │
             │                                    (Pre-calculates Fundamental 
             │                                     Scores & Filter Whitelist)
             │                                              │
             │                                              ▼
             │                                     [Firebase DB / Cache Store]
             │                                              │
             │                                              ▼
[ User Opens Android Application ] ────────────────► [On-App-Open Dynamic Engine]
             │                                              │
             └────────────────► (Fetches Live Price) ───────┼──► Merges Cached Fundamentals
                                                                with Live Price Action &
                                                                Runs Real-Time Indicators &
                                                                Candlestick Scan.
                                                                │
                                                                ▼
                                                       [Daily Smart Dashboard]


The Daily Batch Processing Engine (Pre-Computed Fundamentals):

Runs once daily at 08:30 IST (pre-market).

Parses deep public corporate reports (Balance Sheets, P&L, Cash Flow statements) for all NSE-listed equities.

Calculates 15 core financial ratios, applies fundamental health scoring, and outputs a Whitelisted Stock Pool of companies that meet or exceed fundamental safety baselines.

Saves this heavy computed data to a lightweight, scalable document database (e.g., Firebase Firestore/AWS DynamoDB) to minimize run-time database lookups.

The On-App-Open Dynamic Engine (Real-Time Technical/Candlestick Processing):

Triggers automatically the moment a user opens the application during and post-market hours.

Retrieves the pre-filtered fundamental Whitelisted Stock Pool.

Fetches live technical data and real-time/latest price candles (via low-cost, high-performance streaming or optimized REST polling).

Dynamically runs the complete technical indicator and candlestick pattern recognition suite over the live charts of whitelisted stocks.

Combines both layers into a real-time, high-conviction Confidence Score on the client’s screen instantly.

5. Feature Deep Dive & Functional Specifications

5.1 The Deep Fundamental Filtering Module (Corporate Reports Analysis)

To prevent beginner capital from being trapped in fundamentally deteriorating or speculative penny structures, the engine first parses published financial reports using a comprehensive library of major financial ratios.

A. Valuation & Pricing Ratios

Price-to-Earnings Ratio ($P/E$): Measures the market premium. Evaluated relative to peer groups and historical median.


$$P/E = \frac{\text{Current Market Price per Share}}{\text{Earnings per Share (EPS)}}$$

Price-to-Book Ratio ($P/B$): Evaluates asset-backed value, especially critical for banking, financial services, and capital-intensive sectors.


$$P/B = \frac{\text{Current Market Price per Share}}{\text{Book Value per Share}}$$

Enterprise Value-to-EBITDA ($EV/EBITDA$): Evaluates corporate valuation independent of capital structures.


$$EV/EBITDA = \frac{\text{Market Capitalization} + \text{Total Debt} - \text{Cash and Cash Equivalents}}{\text{EBITDA}}$$

PEG Ratio (Price/Earnings-to-Growth): Screens out high $P/E$ companies that do not possess corresponding earnings growth dynamics.


$$PEG = \frac{P/E \text{ Ratio}}{\text{Annual EPS Growth Rate}}$$

B. Solvency & Financial Health Ratios

Debt-to-Equity Ratio ($D/E$): Eliminates companies burdened by excessive, high-risk leverage.


$$D/E = \frac{\text{Total Liabilities}}{\text{Total Shareholder Equity}}$$

Current Ratio: Assesses short-term liquidity buffer.


$$\text{Current Ratio} = \frac{\text{Current Assets}}{\text{Current Liabilities}}$$

Quick Ratio: Measures immediate solvency by excluding inventory.


$$\text{Quick Ratio} = \frac{\text{Current Assets} - \text{Inventory}}{\text{Current Liabilities}}$$

Interest Coverage Ratio ($ICR$): Confirms the company can service debt comfortably from operating profits.


$$ICR = \frac{\text{Earnings Before Interest and Taxes (EBIT)}}{\text{Interest Expense}}$$

C. Profitability & Return Metrics

Return on Equity ($ROE$): Evaluates the efficiency of shareholder capital utilization.


$$ROE = \frac{\text{Net Income}}{\text{Total Shareholder Equity}}$$

Return on Capital Employed ($ROCE$): Measures profitability of both debt and equity deployment.


$$ROCE = \frac{\text{EBIT}}{\text{Capital Employed}}$$

Operating Profit Margin ($OPM$): Tracks core pricing power and efficiency.


$$OPM = \left( \frac{\text{Operating Profit}}{\text{Net Revenue}} \right) \times 100\%$$

Net Profit Margin ($NPM$): Calculates ultimate bottom-line earnings yield.


$$NPM = \left( \frac{\text{Net Profit}}{\text{Net Revenue}} \right) \times 100\%$$

D. Operational Efficiency & Growth Dynamics

Asset Turnover Ratio: Measures how efficiently assets generate sales revenue.


$$\text{Asset Turnover} = \frac{\text{Net Sales}}{\text{Average Total Assets}}$$

Working Capital Turnover Ratio: Determines efficiency in funding day-to-day operations.


$$\text{Working Capital Turnover} = \frac{\text{Net Sales}}{\text{Average Working Capital}}$$

Free Cash Flow Yield ($FCF$ Yield): Evaluates pure cash generativity, weeding out paper-only profitability.


$$FCF \text{ Yield} = \frac{\text{Operating Cash Flow} - \text{Capital Expenditures}}{\text{Market Capitalization}}$$

Promoter Holding Trend: Flags risk of corporate governance issues if promoter holdings have steadily declined or are heavily pledged.

5.2 The Comprehensive Technical Momentum Module

Once a stock passes the Fundamental Filter, its short-to-medium-term price momentum is evaluated utilizing a vast, multi-timeframe technical indicator matrix.

Indicator Category

Technical Instrument

Standard Mathematical Configurations

Operational Trade Signal

Trend Following

Simple Moving Averages (SMA)

$50$-day, $100$-day, $200$-day SMAs

Bullish alignment when $\text{Price} > 50\text{ SMA} > 100\text{ SMA} > 200\text{ SMA}$.

Trend Momentum

Exponential Moving Averages (EMA)

$20$-day, $50$-day EMAs

Interday pullbacks to $20/50\text{ EMA}$ indicate high-probability swing buy opportunities.

Momentum Oscillator

Relative Strength Index (RSI)

$14$-period lookback (Interday)

Identifies overextended trends. Buy zone: pullbacks to $40-50$ in an uptrend. Avoid overbought ($>75$).

Momentum Crossover

MACD Oscillator

$12$-day, $26$-day, $9$-day Signal line

Bullish MACD histogram expansion and signal line crossover in positive territory.

Volatility Ranges

Bollinger Bands

$20$-period SMA, $2$ Standard Deviations

Identifies contraction/squeeze phases indicating impending breakout moves.

Key Structural Levels

Fibonacci Retracement Levels

Peaks & troughs of the current primary wave

Identifies structural entry points at key Golden Ratio levels: $38.2\%$, $50\%$, and $61.8\%$.

Trend Strength

Average Directional Index (ADX)

$14$-period ADX with $+DI$ and $-DI$

Strong trend validation when $ADX > 25$ with $+DI > -DI$.

Volume Inflow

On-Balance Volume (OBV)

Cumulative running volume total

Confirms price expansion is backed by volume accumulators rather than low-liquidity speculation.

Cash Inflow

Chaikin Money Flow (CMF)

$21$-period volume-weighted distribution

Evaluates institutional accumulation. Score $>0.15$ signals strong systemic buying.

5.3 Extensive Candlestick Pattern Recognition Engine

The dynamic on-app-open engine scans the latest completed daily candle and ongoing live session candle structures against a massive automated recognition catalog:

A. Single Candle Reversal & Continuation Patterns

Hammer / Inverted Hammer: Found at swing lows; represents strong intraday price rejection of lower levels, shifting structural momentum.

Shooting Star / Hanging Man: Found at local swing highs; signals distribution and profit-taking patterns.

Doji (Standard, Dragonfly, Gravestone): Signals extreme market equilibrium and imminent breakouts or reversals depending on trend location.

Bullish & Bearish Marubozu: Denotes absolute dominance of one side; body contains minimal or no wicks. Signals continuation.

Spinning Tops: Reflects deep indecision, setting up localized support/resistance boundaries.

B. Double Candle Reversals

Bullish Engulfing / Bearish Engulfing: The body of the second day completely overlaps the body of the first day, representing strong capital accumulation or heavy distribution.

Bullish Harami / Bearish Harami: A small second candle completely contained inside the first candle's body, indicating a contraction pattern and potential breakout.

Tweezer Bottoms / Tweezer Tops: Identifies immediate, identical rejection of a specific low/high price boundary over consecutive days.

Piercing Line / Dark Cloud Cover: Reversal patterns involving a gap open and immediate, heavy retracement deep into the prior candle's body ($>50\%$).

C. Triple Candle Reversals

Morning Star / Evening Star: The gold standard of swing reversals. Consists of a long directional candle, an indecisive gap candle, and a robust counter-directional breakout candle.

Three White Soldiers / Three Black Crows: Strong momentum confirmation through progressive, high-volume consecutive trend-aligned candles.

5.4 Transparent "Why this Stock?" Synthesis Engine

Equipped with a proprietary natural language generation (NLG) template parser, MarketMentor translates complex, multi-factor data points into a clear, cohesive, beginner-friendly narrative.

Rule Engine Synthesis: The system parses the exact technical, fundamental, and candlestick flags that triggered the recommendation and presents them in plain language.

Example Output Structure:

"TATA MOTORS is recommended as a STRONG BUY today because of a strong confluence of fundamentals and structural chart triggers:

Fundamental Strength: The company has reduced its Debt-to-Equity ratio from $1.4$ to a healthy $0.3$, and maintains a robust ROCE of $21\%$, demonstrating efficient capital use.

Technical Trigger: The stock price has successfully found support at its 50-day EMA and bounced, accompanied by an above-average volume spike ($+40\%$ vs. $10$-day median).

Candlestick Pattern: A clear Bullish Engulfing pattern formed on yesterday’s daily close, confirming that institutional buyers are aggressively protecting this price level.

Risk Profile: Moderate. Although fundamentals are strong, the stock’s RSI is currently at $68$, suggesting it is approaching near-term overbought levels. Set strict stop-losses."

5.5 Virtual Trading System (Paper Portfolio Ledger)

To cultivate risk management and allow users to validate MarketMentor's recommendations safely, the application integrates a robust virtual trading experience.

Virtual Starting Capital: Every user begins with virtual capital of $₹10,00,000$.

Delivery Only Constraints (Strict Enforcement):

No Short Selling: Users can only sell shares they currently hold in their virtual portfolio (Delivery Sell).

No Leverage: All virtual purchases must be fully funded by available cash reserves (Cash Delivery Model). No margin trading allowed.

No Intraday Settlement: To simulate realistic interday trading, any position bought can only be sold on the following trading day ($T+1$) or later. Immediate intraday square-offs are blocked.

Advanced Analytics Ledger:

Win Rate: Ratio of profitable trades to total closed trades.

Average Return: Calculated as:


$$\text{Average Return} = \frac{\sum (\text{Return Percentage per Trade})}{\text{Total Trades Executed}}$$

Profit Factor: Ratio of gross profits to gross losses.

Max Drawdown: The peak-to-trough decline of portfolio value to track capital preservation discipline.

5.6 Automated Recommendation Performance Tracker

To ensure absolute transparency and build system trust, MarketMentor automatically tracks every recommendation issued.

System Tracking Lifecycle:

When a "Strong Buy" recommendation is published, the system registers a target price ($+10\%$) and a stop-loss price ($-5\%$).

The system actively tracks the stock price until it either hits the target, hits the stop-loss, or reaches the maximum recommended holding period (e.g., $20$ trading days).

Public Scorecard: A publicly visible page within the app displays the historical accuracy of the engine (e.g., "Our recommendations hit their targets $74\%$ of the time over the last 90 days"). This establishes empirical accountability.

6. Non-Functional Requirements (NFRs) & Engineering Guidelines

6.1 Architecture & Low-Cost Infrastructure Management

Serverless Data Sourcing: Leverage AWS Lambda or Google Cloud Functions for daily batch calculations. Functions run once per day at 08:30 IST to compile the whitelist, keeping database execution costs minimal.

Intelligent Local Caching: The Android app must cache the daily fundamental whitelist locally (SQLite / Room Database). Upon subsequent app launches during the day, the app only polls live pricing and runs light technical math client-side, avoiding expensive server-side technical scanning APIs.

Optimized API Consumption: To keep live market data costs near zero for the MVP, the application utilizes delayed data feeds (e.g., 1-5 min delay) if real-time tick-by-tick API data costs are high. Since the focus is strictly on swing trading/interday holding, sub-second latency is not required.

6.2 UI/UX Minimalism & Cognitive Support

Clean Visuals: Redundant option chains, derivatives metrics, and complex tick-by-tick charts must be hidden. Main views should feature simple clean line/candle charts with clear visual callouts.

No Red/Green Noise: Color coding (Green for Buy/Bullish, Red for Sell/Bearish) must be used constructively to guide action, not trigger emotional responses.

Terminology Jargon Buster: Every ratio (e.g., $ROCE$) or pattern (e.g., Bullish Engulfing) on screen must have an interactive tooltip that explains its meaning in one simple sentence.

6.3 Compliance, Security & Ethical Boundaries

SEBI Regulatory Guardrails: MarketMentor is strictly a decision-support and educational tool. The app does not connect to real broker APIs (such as Zerodha, Groww, or AngelOne) to place real money trades. It must feature prominent disclaimers informing users that recommendations are algorithmically generated and do not constitute registered investment advice.

Risk Disclaimers: Every stock recommendation details card must contain a risk disclaimer: "Investing in equities involves market risk. Past performance of this system does not guarantee future virtual or real profits. Trade responsibly."
