CREATE TABLE reference_model_objects (
	id INTEGER NOT NULL PRIMARY KEY,
	reference_model_class_name VARCHAR(100) NOT NULL,
	archetype_id VARCHAR(100),
	node_id CHAR(6),
	absolute_path VARCHAR(1000),
	serialized VARCHAR(10000)
);