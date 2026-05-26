📋 Agile Backlog & Issue Blueprint: MarketMentor

To prove to hiring managers that you know how to operate inside an Agile/Scrum development environment, you should populate your GitHub Repository's Issues tab with professional, developer-ready user stories.

Follow these instructions to set up your backlog on GitHub.

🛠️ Step 1: Initialize Your GitHub Project Board

Go to your marketmentor repository on GitHub.

Click the Projects tab at the top.

Click New project and select the Board layout (this creates a Kanban board with Todo, In Progress, Done columns).

Name the board: MarketMentor Android MVP Roadmap.

📝 Step 2: Create these Three Key Developer Issues

Go to the Issues tab in your repository, click New Issue, and create these three distinct tickets. Copy and paste the Markdown below directly into each issue description.

Ticket 1: Implement Delivery Protection Lock ($T+1$ Settlement Rule)

Title: FEAT: Implement Delivery Protection Lock & T+1 Settlement Interlock in Virtual Ledger

Labels: feature, compliance, high-priority

Description:

### User Story
As a retail beginner using MarketMentor,
I want the virtual ledger to block me from selling any asset purchased during the same trading session,
So that I am forced to practice disciplined, overnight positional swing trading and avoid emotional intraday trading.

### Technical Description
The virtual portfolio ledger must monitor the timestamps and simulated trading day values of all buy orders. Under simulated T+1 settlement rules:
* Any position with `purchaseDay == currentSimulatedDay` must have its status flag set to `isSettled = false`.
* The "Liquidate/Sell" action on unsettled positions must be intercepted.
* Instead of executing, the app must display the **Intraday Liquidity Interlock Modal** explaining the delivery constraints.

### Acceptance Criteria
* **Scenario: User attempts to sell an asset bought today**
  * **Given** that the user holds 50 shares of `TATAMOTORS` purchased on `Day 1`
  * **When** they attempt to click "Sell/Liquidate" on `Day 1`
  * **Then** the system must block the order and render the educational "Intraday Lock" modal.
* **Scenario: User attempts to sell an asset on the subsequent day**
  * **Given** that the user holds 50 shares of `TATAMOTORS` purchased on `Day 1`
  * **When** the timeline advances to `Day 2` and the position's settlement is updated to `isSettled = true`
  * **Then** the "Sell/Liquidate" action must execute successfully at the current market price.


Ticket 2: Educational "Jargon Buster" Overlay Tooltips

Title: FEAT: Develop Educational Tooltip Overlays for Complex Financial Ratios

Labels: feature, ux, low-priority

Description:

### User Story
As a financial beginner,
I want to click on any complex metric or ratio (like ROCE or P/E) on the stock analysis cards,
So that I can see an instant, non-technical definition explaining what the metric is and why a product manager tracks it.

### Technical Description
Build a lightweight, reusable bottom-sheet or modal dialog controller for Android that takes a metric code (e.g., `PE`, `DE`, `ROCE`, `ENGULFM`) and renders custom string resources:
1. Metric Name & Category
2. Basic Mathematical Formula
3. A non-technical explanation
4. "PM Perspective" - explaining the risk guardrail significance.

### Acceptance Criteria
* **Given** the user is viewing the "Corporate Fundamentals" tab of a stock card
* **When** they click on the `ROCE` metric card or its question mark icon
* **Then** a clean overlay dialog must appear containing the definition, formula, and the PM perspective.
* **And** clicking "Acknowledge" or tapping outside must dismiss the dialog immediately without losing the current dashboard state.


Ticket 3: Dynamic Pre-Market Batch & Live-Merge Sourcing

Title: CHORE: Architecture Design for Pre-Market Batch Whitelist & Live Client Merging

Labels: architecture, database, medium-priority

Description:

### User Story
As a Product Manager,
I want our system to batch calculate corporate report fundamentals pre-market on a serverless function,
So that we minimize expensive database lookups and run-time processing costs on our users' devices.

### Technical Description
Design the hybrid data processing flow:
1. **Serverless Function (AWS Lambda / Cloud Function):** Executes daily at 08:30 IST. Fetches fundamental metrics of listed stocks, scores them, and writes the fundamentally sound assets to the Firestore Whitelist DB.
2. **Android Client-Side App:** Upon app opening, fetches this pre-filtered Whitelist cache, polls latest live price ticks, and dynamically runs the technical and candlestick recognition algorithms client-side.

### Acceptance Criteria
* **Given** it is 09:15 IST (pre-market opens)
* **When** the serverless function executes successfully
* **Then** a refreshed, indexed JSON document of whitelisted stocks must be written to Firestore.
* **And** the Android application must pull this JSON on launch, running technical momentum math on client execution threads to achieve sub-second loading speeds.
