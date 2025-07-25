# Information Flow in the Diagram

### anchor focus frontend → anchor app api

Information:
- Authentication & Authorization Information: User login session information, authentication tokens, refresh tokens, and access control data.
- Session Start/End Requests: Requests for users to start or end focus sessions.
- User Settings: Content configured by users in the app (e.g., focus session duration, notification settings, theme preferences, and accessibility options).
- Feedback Input (Indirect): Initial requests for sending session-end feedback via API.
### anchor focus frontend → anchor insight AI

Information:
- Real-time Video Stream: User's camera input (via OpenCV) and PC screen sharing video stream data. This is sent directly to the AI module for real-time analysis. This path is chosen when passing large amounts of stream data directly to AI with low latency without going through the API layer.
- Session Identifier: Information to identify which user and which session the data comes from (such as session ID obtained from API).

### anchor app api ↔ anchor insight AI

Information (anchor app api → anchor insight AI):
- User ID/Session ID: IDs to identify which user's which focus session data processing is requested to the AI module.
- Past User Data (On Request): When anchor insight AI needs past session data or user profile information (stored in MySQL or NoSQL) for analysis, it may request via API.
- User Feedback (Text Format): Text feedback such as "User Retrospective (Text)" input by users at session end. This is sent to AI as analysis request via API.
- User Log Time: User's focus session log time data (if available).

Information (anchor insight AI → anchor app api):
- AI Analysis Results Summary/Aggregation: Aggregated values and summaries of analysis results such as user distraction time from ViT, user presence/behavior predictions from YOLO.
- LLM Advice/Insights: Text data of "Actions" (specific steps users should take) and "Insights" (analysis to understand user behavior) generated by LLM.
- Focus Score: "Focus evaluation (score)" presented to users at session end.
- Real-time Notification Triggers: Trigger information and message content to display "real-time notifications to get users back on track" on the frontend when users are determined to have lost focus.

### anchor insight AI ↔ anchor app api

Arrows 3 and 4 represent bidirectional communication, where the information explained above is exchanged mutually. For example, analysis requests from API to AI and return of analysis results from AI to API.

### anchor app api → MySQL

Information:
- User Basic Information: User registration information, profile data.
- Authentication Information: User login credentials (hashed passwords, etc.).
- Session Metadata: Start/end times of each focus session, session ID, session length, etc.
- App Settings: User-specific application settings.
- Billing Information: User subscription and payment-related data (if any).
- Focus Score (Aggregated): Aggregated data of final focus scores received from anchor insight AI that are determined to be managed by MySQL.
- etc.

### anchor insight AI → NoSQL

Information:
- AI Model Raw Data/Intermediate Results: Data converted to strings, numbers, and boolean types from processed image/video data obtained from OpenCV and screen sharing.
- ViT Analysis Detailed Data: Detailed logs of user distraction time (when, how long they were distracted, etc.).
- YOLO Analysis Detailed Data: User keypoint data, flags for each time point indicating whether they were in front of the screen, detailed logs of predicted behaviors.
- LLM Input and Output Logs: User text feedback (User Retrospective), context passed to LLM, raw data of specific advice (Actions, Insights) generated by LLM.
- User Evaluation of Suggestions: Evaluations made by users on advice (User Rating on Suggestions).
- Pre-trained Data: Knowledge base data such as "knowledge about self-discipline from the internet (Pretrained Data)" that AI references.
- AI Model Training Data/History: Past analysis data accumulated for AI model improvement and information about learning processes.
- Long-term User Behavior Pattern Data: Data showing user behavior trends and improvement levels across multiple sessions.

### anchor insight AI → Anchor flow

Information:

* Finalized AI-Driven Insights: Consolidated recommendations and insights packaged for the core Anchor workflow engine.
* Workflow Triggers: Events or flags (e.g., session completed, threshold of distraction) that instruct the Anchor flow module to initiate follow-up actions (notifications, message of intervention actions).
* Data Payloads: Summarized session metrics, user performance trends, and suggestion payloads handed off to the Anchor flow orchestrator.

---

*Additional Notes*

* Although not explicitly shown in the diagram, it is assumed that NoSQL stores AI training data and long-term user behavior logs, and `anchor insight AI` **reads** this data. This is necessary especially when utilizing "Pretrained Data" or when models learn from past user behavior.
* The information indicated by each arrow will be further detailed in the specific system design phase and defined as data schemas and API specifications.
* pricing model .
  * those 5 or 3 for basic, 7$ along with AI companion.
