# DevSync Project

## Project Context
DevSync emerged from a collaborative effort aimed at addressing the gaps in existing task management tools. Driven by a commitment to innovation, the development team chose JAKARTA EE to create a robust platform. DevSync's features, such as advanced search with tags, scheduling constraints, and automated status updates, were meticulously designed based on user feedback. The project envisions DevSync as a catalyst for enhanced collaboration and project success, aiming to simplify the complexities of task management for individuals, team leaders, and managers in dynamic work environments.

## Problems Addressed and Solutions Offered

### Problems
1. **Inefficient Task Management**:
    - Existing tools lack advanced search and filtering options.
    - Difficulties in maintaining up-to-date task statuses and schedules.
    - Inability to enforce task creation and completion rules.

2. **Poor Collaboration**:
    - Limited functionality for team collaboration and task assignment.
    - Inadequate visibility for managers over team progress and task status.

3. **Inflexible Scheduling**:
    - Lack of constraints on task scheduling leading to missed deadlines.
    - Inability to handle complex scheduling requirements.

### Solutions
1. **Advanced Task Management**:
    - **Advanced Search with Tags**: Allows users to efficiently find and filter tasks.
    - **Automated Status Updates**: Keeps task statuses up-to-date based on predefined rules.
    - **Enforced Task Rules**: Prevents tasks from being created in the past and ensures tasks are completed before deadlines.

2. **Enhanced Collaboration**:
    - **Multi-tag Requirement**: Users must enter multiple tags for better organization and searchability.
    - **Task Assignment Restrictions**: Users can only assign additional tasks to themselves, ensuring accountability.
    - **Manager Overview**: Provides managers with a comprehensive view of all tasks assigned to their employees, including completion percentages and token usage.

3. **Flexible Scheduling**:
    - **Scheduling Constraints**: Limits task scheduling to 3 days in advance to prevent overloading.
    - **Token-based Task Replacement**: Allows users to replace tasks assigned by managers using a token system, adding flexibility while maintaining control.
    - **Automated Deadline Management**: Marks tasks as incomplete if they exceed their deadlines without being completed.

## Version 1.0.0

### Setup and Installation
1. **Evaluate Development Environment Requirements**:
    - IDE
    - Application Server
    - Database

2. **Install and Configure Development Environment**:
    - Set up the development environment on developers' machines.
    - Design a robust JEE architecture to meet application needs.
    - Establish a well-organized project structure for code management.

3. **CRUD Functionality for User Class**:
    - **Attributes**:
        - `id`
        - `username`
        - `password`
        - `first_name`
        - `last_name`
        - `email`
        - `manager`

### Required Technologies
- JEE Technologies: Maven, JAKARTA EE, Hibernate, Tomcat/JBoss/GlassFish, JPA, Servlets, JSP

## Version 1.1.0

### Features
1. Ensure tasks cannot be created in the past.
2. Require users to enter multiple tags for tasks.
3. Restrict task scheduling to 3 days in advance.
4. Enforce that marking a task as complete must be done before the deadline.
5. Users can assign additional tasks to themselves, not to others.
6. To replace a task assigned by your manager, you have 2 tokens per day. You have one token for deletion per month.
7. Deleting a task created by the same user does not affect tokens.

### Deployment
After implementing the above features, deploy your application to your local repository and proceed to the next version.

## Version 1.2.0

### Features
1. When a manager replaces a task with another, it must be assigned to another user and cannot be modified or deleted by tokens.
2. If the manager does not respond to a task change request within 12 hours, the user receives double modification tokens the next day.
3. Every 24 hours, the application must mark tasks as incomplete if they are not marked as done and have exceeded the deadline.
4. Allow managers to view an overview of all tasks assigned to their employees, including a completion percentage filtered by tags for the week, month, and year. Also include the number of tokens used.

### Deployment
After implementing the new features, deploy your application to your local repository.

## 🛠 Skills
<p>
    <img src="https://skillicons.dev/icons?i=git,idea,java,jakarta,postgres,maven" height="45" alt="skills"  />
</p>
