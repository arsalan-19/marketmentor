Figma Wireframe & Design Specifications

This document outlines the visual structure, layout guidelines, and interactive design paradigms developed for MarketMentor to ensure developer-ready alignment.

1. Design System & Style Library

To communicate security, intelligence, and institutional clarity to the user, our user interface leverages a highly tailored, clean color scheme:

1.1 Palette Specifications

Brand Primary Indigo: #4F46E5 (Signifies intelligence and institutional focus)

Brand Dark Navy: #0F172A (Establishes structural depth and stability)

Capital Protection Emerald: #10B981 (Used conservatively to indicate valid safety thresholds)

Risk Alert Rose: #EF4444 (Highlights breaches of safety parameters or risk zones)

Slate Background: #F8FAFC (Promotes readability and limits visual fatigue)

2. Core Interactive Screen Blueprints

2.1 Screen 1: The Daily Smart Dashboard (Home)

The primary interface presented to users upon daily opening. It summarizes the active, whitelisted recommendation pool.
<img width="1035" height="505" alt="image" src="https://github.com/user-attachments/assets/8bdfe28f-33d6-4706-bc41-3f252b67ac80" />



2.2 Screen 2: Multi-Factor Analysis Drawer (Detail View)

Triggered when a user selects a stock card. Highlights narrative evaluation over financial figures.

Section 1: The "Why This Stock" Synthesis Card:
A highlighted, slate-colored banner featuring bulleted narrative structures. This guarantees the user reads the plain-language explanation before looking at mathematical formulas.

Section 2: Interactive Financial Parameter Blocks:
A grid layout featuring four main ratio categories:

Valuation: Price-to-Earnings Ratio ($P/E$)

Solvency: Debt-to-Equity Ratio ($D/E$)

Profitability: Return on Capital Employed ($ROCE$)

Momentum: Technical Relative Strength Index ($RSI$)

Tooltip Interaction: Clicking any metric card opens an overlay containing its dictionary definition and strategic significance.

Section 3: Simulated Action Footnote:
Features a quantity selector field and a large button: Simulate Swing Buy.

2.3 Screen 3: Virtual Portfolio Ledger (Ledger View)

Tracks asset values, performance scores, and trade logs.

Primary KPI Cards: Large-format displays for Win Rate %, Average Return %, and Portfolio Max Drawdown %.

Simulated Holding Table: Displays Stock ticker, Avg Purchase Price, CMP, Total Invested, and Live P&L in real-time.

Execution Block Warning: Triggering "Sell" on a stock purchased in the ongoing daily session yields an immediate modal reminding the user of the delivery constraints.

3. Design Tokens & Mock Assets

Interactive Wireframe Link: Figma Design File Placeholder

Typography Rule: Primary: Inter (Regular, Medium, Semi-Bold, Extra-Bold). Optimized for multi-ratio statistical tables and structured list items.
