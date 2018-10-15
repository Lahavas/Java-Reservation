INSERT INTO room (id, name) VALUES (1, 'Room 1');
INSERT INTO room (id, name) VALUES (2, 'Room 2');
INSERT INTO room (id, name) VALUES (3, 'Room 3');
INSERT INTO room (id, name) VALUES (4, 'Room 4');

INSERT INTO reservation (id, room_id, owner, start, end) VALUES (1, 2, '주인', '2018-10-20T13:00:00', '2018-10-23T14:30:00');
INSERT INTO reservation (id, room_id, owner, start, end) VALUES (2, 2, '다른 주인', '2018-10-18T13:00:00', '2018-10-20T12:30:00');