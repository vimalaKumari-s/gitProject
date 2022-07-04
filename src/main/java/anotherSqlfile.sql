CREATE TABLE `emf_event_logs` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) NOT NULL,
  `event_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL)