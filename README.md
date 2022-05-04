# Khenan Terry Project 1:

## Elastic Beanstalk Link
- [Khenan Terry's deployed P1](http://khenanterryp1restapi-env-1.eba-2ip9ja89.us-east-1.elasticbeanstalk.com/)

## Tables

### ERD Diagram

![ERD Diagram](https://raw.githubusercontent.com/k-n-terry/khenan_terry_p1/master/postgreSQL/kt_p1_ERD_02.png)

### Table 01: Employee
- Column 1 is the employee ID, which is the primary key
    - It is generated by MD5 algorithm from the firstname & lastname.
    - This is generated on creation, but it is not edited.
- Column 2 is the employee's firstname (string).
- Column 3 is the employee's lastname (string).
- Column 4 is the employee's registry status (string): Listed/Unlisted.

### Table 02: Expenses
- Column 1 is a serial primary key (int).
- Column 2 is the foreign key, which refers back to the primary key in *employee*.
    - It is not unique, because all expenses for a particular employee will be tracked by the employee ID.
- Column 3 is a label for the expense (string).
- Column 4 is the expense amount (numeric to 2 decimal places).
- Column 5 is the approval status of the expense (string): Approved, Denied, Pending.

## Routes

### Delete
- To comply with the expectations for P1 expense entries are unable to be deleted.
- Therewith employees may also not be deleted, as the foreign key constraints prevent this. Instead of truly "deleting" am employee row, the delete-route modifies the "registry" field for an employee to "Unlisted". If the employee is unlisted, then expenses may not be posted for the corresponding employee ID.

## Pattern

### Main Packages

![Main Packages](https://raw.githubusercontent.com/k-n-terry/khenan_terry_p1/master/notes/program_structure.png)

### Logger

![Logger](https://raw.githubusercontent.com/k-n-terry/khenan_terry_p1/master/notes/logger.png)

### IntelliJ Tests

![IntelliJ Tests](https://raw.githubusercontent.com/k-n-terry/khenan_terry_p1/master/notes/intelliJ_tests.png)

### Postman Tests

![Postman Tests](https://raw.githubusercontent.com/k-n-terry/khenan_terry_p1/master/notes/postman_tests.png)