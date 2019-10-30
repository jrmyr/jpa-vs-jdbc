# JPA vs. JDBC

A little project to compare a JPA vs a JDBC approach.

## Usage
1. Create a local docker-based test database: `cd` into folder `./scripts` and execute `re_create_pg_db_container.sh`.
2. Start the application (via IDE or via gradle task `bootRun`)

## Purpose
The purpose is to check/show the differences between a JPA-based (incl. lazy loading) and a JDBC-based data access
approach. In this example app the lazy fetching might make not too much sense in terms of optimization but the goal is
to evaluate the impact compared to JDBC.
