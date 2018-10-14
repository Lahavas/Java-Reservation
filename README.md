Room Reservation System
========================

Problem Solve
-------------

- 회의실 추가
    - Input Data Form
        ```
        {
            owner: String,
            period: {
                start: DateTime,
                end: DateTime
            },
            room: {
                name: String
            },
            until: Date
        }
        ```
        - period는 예약 시작 시간과 예약 종료 시간을 의미합니다
        - room은 회의실에 대한 정보를 담고 있습니다
        - until은 반복 기간을 의미합니다
            - until이 `null`이면 1회성 예약입니다
            - until이 `Date type`이면 반복 예약입니다
                - start와 end를 until 이전까지의 모든 주의 동일한 요일의 다른 날짜에 대하여 회의실 추가를 반복합니다
    - Service Logic
        - 데이터베이스에서 입력받은 room과 동일한 예약 정보들을 전부 불러옵니다
        - 이후에 중복 탐색은 `Greedy Algorithm`으로 진행하였습니다
            - 입력받은 period를 start에서 end까지 30분 간격으로 분리합니다
            - 불러온 예약 정보들과 겹치는지 전부 탐색합니다
        - 만약 중복되는 예약이 존재하지 않는다면 입력받은 데이터를 가공하여 데이터베이스에 넣습니다

Usage
-----

- result Directory의 Executable JAR file을 통해 deploy 가능
    - `java -jar result/reservation-0.0.1-SNAPSHOT.jar`
