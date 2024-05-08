# API

## Functions (endpoints)

### **/api/v1/subscribe POST**
    - headers:
         - x3-trace-id
         - x3-span-id
         - x3-sc-name
         - x3-rq-type (dev,stub,uat,prod)
         - x3-business-key (idempotency) opt
    - body:
        - system:
            - rqUid
            - rqTime
        - subscriber:
            - category vc
            - city
            - email/phone
    - repsonse:
        - system:
            - responseType
            - resultStatus
            - errors:
                - code
                - group
                - field
                - message

### **/api/v1/canceled POST**
    - headers:
         - x3-trace-id
         - x3-span-id
         - x3-sc-name
         - x3-rq-type (dev,stub,uat,prod)
         - x3-business-key (idempotency) opt
    - body:
        - system:
            - rqUid
            - rqTime
        - subscriber:
            - email/phone
    - repsonse:
        - system:
            - responseType
            - resultStatus
            - errors:
                - code
                - group
                - field
                - message

### **/api/v1/vacancies POST**
    - headers:
        - x3-trace-id
        - x3-span-id
        - x3-sc-name
        - x3-rq-type (dev,stub,uat,prod)
        - x3-business-key (idempotency) opt
    - body:
        - system:
            - rqUid
            - rqTime
        - model (hh, habr etc)
        - filters:
            - array of type for filters (salary,city,category etc)
    - repsonse:
        - system:
            - responseType
            - resultStatus
            - errors:
                - code
                - group
                - field
                - message
        - vacancies:
            - array

### **/api/v1/start POST**
    - headers:
        - x3-trace-id
        - x3-span-id
        - x3-sc-name
        - x3-rq-type (dev,stub,uat,prod)
        - x3-business-key (idempotency) opt
    - body:
        - system:
            - rqUid
            - rqTime
        - filters:
            - array of type for filters (salary,city,category etc)
    - repsonse:
        - system:
            - responseType
            - resultStatus
            - errors:
                - code
                - group
                - field
                - message
        - stat:
            - array

## Entity description vc (vacancy)

1. Info (HH model)
    1. Area:
       - id
       - name
       - url
    2. Salary:
       - from
       - to
       - currency
       - gross
    3. Address:
       - city
       - street
       - building
       - lat
       - lng
    4. Employer:
       - id
       - name
       - url
    5. Snippet:
       - requirement
       - responsibility
    6. Schedule:
       - id
       - name
    7. Experience:
       - id
       - name
    8. Item (Vacancy):
       - id
       - premium
       - name
       - department
       - area
       - salary
       - address
       - published_at
       - created_at
       - url
       - employer
       - snippet
       - contacts
       - schedule
       - experience

