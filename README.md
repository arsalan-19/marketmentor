📈 MarketMentor: AI-Powered NSE Swing Investment Assistant

A highly disciplined, multi-factor algorithmic research assistant engineered to democratize institutional-grade equity analysis for retail investors in the Indian Stock Market (NSE).

🔗 👉 RUN THE LIVE INTERACTIVE PROTOTYPE 👈

🚀 Project Overview & Architecture Vision

MarketMentor is a native Android application concept (architected for Kotlin and Jetpack Compose) designed to bridge the structural gap between raw, complex corporate financial disclosures and systematic retail execution.

To protect retail capital from speculative volatility, MarketMentor enforces a strict delivery-only ($T+1$ holding) boundary. The platform prohibits all high-risk speculative intraday, leverage, and complex derivative (Futures & Options) activities. It forces the user to focus exclusively on studying stable, multi-week interday swing equity trends.

Why an Android App with a Web Prototype?

Android Target: Since the Indian FinTech landscape is heavily mobile-first, MarketMentor is fully mapped, wireframed, and designed as a native Android application.

Interactive Web Companion: To remove friction for hiring managers and recruiters, we have compiled a web-based companion simulator. This allows anyone to instantly experience the application's underlying logic, technical indicators, and safety boundaries directly from their web browser.

💼 Product Thesis (The PM Objective)

As a Product Manager in the FinTech space, I identified a critical market failure: Over 70% of new retail investors in the Indian markets sustain heavy capital losses within their first year due to speculative day trading and emotional biases.

Core Product Hypotheses:

The Accessibility Gap: Retail investors do not require automated, black-box trading bots; they need transparent, educational validators.

Explainable Analytics: Providing a plain-English, template-driven narrative explaining why a stock meets swing parameters builds user trust more effectively than a standard rating signal.

Engineering Efficiency: High-grade analysis can run on consumer-level infrastructure by splitting processing into server-side pre-market fundamental batches and lightweight client-side technical/candlestick calculations upon application open.

🛠️ System Capabilities & Core Modules

1. Daily Pre-Market Screening Engine

Runs every morning at 08:30 IST. It automatically ingests official NSE public listings and filters a "Fundamental Whitelist" using 15 core balance sheet and valuation ratios:

Valuation Metrics: Price-to-Earnings ($P/E$), Price-to-Book ($P/B$), $EV/EBITDA$, and $PEG$ ratios.

Solvency Checks: Debt-to-Equity ($D/E$) limits, Current Ratio, and Interest Coverage Ratio ($ICR$).

Profitability Margins: Return on Capital Employed ($ROCE$) and Operating Profit Margin ($OPM$).

2. On-App-Open Dynamic Technical & Candlestick Parser

When the user accesses the platform, the application merges local whitelisted fundamentals with live, real-time market candlestick feeds:

Indicators: 20/50/200 Day SMAs, RSI (14), MACD, and Chaikin Money Flow ($CMF$).

Candlestick Pattern Suite: Recognizes Morning/Evening Stars, Bullish/Bearish Engulfing, Hammers, and Dojis.

3. Natural Language Synthesis Engine

Translates quantitative mathematical factors into clear, structured, and risk-adjusted written narratives:

[SYNTHESIZED RATIONALE]
TATA MOTORS is ranked as a STRONG BUY today based on 3 core alignments:
1. Fundamental Solvency: Debt-to-Equity reduced from 1.4 to 0.3 across consecutive quarters.
2. Technical Momentum: Found strong historical support at the 50-day EMA with positive volume expansion.
3. Candlestick Trigger: Completed daily session formed a validated Bullish Engulfing pattern.


4. Capital Protection Sandbox (Virtual Ledger)

Enforces cash delivery execution rules (no margin leverage, $T+1$ interday holding constraints, short-selling prohibited).

Displays critical risk-performance metrics: Win Rate %, Average Return %, and Peak Portfolio Drawdown %.

📁 Repository Deliverables

Explore the full suite of product development assets created for MarketMentor:

<img width="976" height="145" alt="image" src="https://github.com/user-attachments/assets/66902f80-8fe8-458b-ac32-b583ae05dbce" />


📊 Success Metrics (KPI Framework)

1. Engagement Metrics

DAU/MAU Ratio: Target $>45\%$, measuring user habit loops surrounding the morning pre-market research routine.

Mean Session Duration (Morning): Target $4-6$ minutes, showing deep reading of educational explanations.

2. Algorithmic Metrics

System Alpha: Comparing the "Strong Buy" recommendation basket performance against the NIFTY 50 index over a rolling 90-day window.

🧑‍💼 Professional Details & Networking

Designed, managed, and engineered by [Your Name]

💼 LinkedIn: https://www.linkedin.com/in/arsalan-dawalatabad/

📧 Email: arsalan.daw@gmail.com
