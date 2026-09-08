# OneHealth Task

## Features:

- Patient List Screen: Parses a JSON file to load patients into a Recycler View. A "No Patients Found" screen can be found if the database is empty.
- Patient Details Screen: When a patient is clicked, further details are displayed in a separate screen.
- Dual Language Support: Both English and Arabic text is supported where it can be applied. May be switched using a button.
- General UX|UI: Attempts to follow One Health's color scheme and UI elements where possible.

## Project Structure:

- Activities:
  - PatientListActivity: The main activity screen that displays the recycler view. The JSON database is parsed through this activity and is sent to the recycler view's adapter.
  - PatientDetailsActivity: The activity shown whenever a user clicks on a patient for more info.
 
- Data Classes:
  - Patient: Data class that represents the patient schema from the JSON database. Used by the recycler view to access an patient's information.

-Rec. View Adapters:
  - PatientViewAdapter: The adapter for (PatientDetailsActivity)'s Rec. View. Listens for user input and start's the next intent (PatientDetailsActivity)


