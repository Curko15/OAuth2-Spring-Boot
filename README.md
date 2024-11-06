QR Ticket Generation Application

This project is a web application built using Spring Boot and PostgreSQL. The application generates and manages QR codes for various ticketing purposes. The QR codes link to unique ticket details that are stored in the database, with an authentication layer to restrict access to ticket information.

Key Features

    Public Landing Page:
        Displays the total number of tickets generated so far.

    Ticket Generation Endpoint:
        Allows generating a ticket by accepting JSON data containing vatin, firstName, and lastName.
        Each ticket is generated with a unique identifier (UUID) and timestamp.
        For each vatin, a maximum of 3 tickets can be generated.
        Returns a QR code image that links to a ticket details page.
        Uses OAuth2 Client Credentials authorization for secure API access, implemented via Auth0.

    Ticket Details Page:
        Each ticket has a dedicated details page accessible only to authenticated users.
        The currently logged-in user’s name is displayed on the page using OpenID Connect.

    Authentication and Authorization:
        Implemented using OAuth2 and OpenID Connect with Auth0.

Tech Stack

    Backend Framework: Spring Boot
    Database: PostgreSQL
    Authentication: Auth0 (OAuth2 and OpenID Connect)
