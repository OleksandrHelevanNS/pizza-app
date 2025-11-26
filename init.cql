CREATE KEYSPACE IF NOT EXISTS menu
    WITH replication = {
        'class': 'SimpleStrategy',
        'replication_factor': 1
        };
