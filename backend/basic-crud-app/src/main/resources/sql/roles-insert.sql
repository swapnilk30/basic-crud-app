INSERT INTO roles (
    name,
    deleted,
    version,
    created_at,
    updated_at,
    created_by,
    updated_by
)
VALUES
    ('ROLE_ADMIN', b'0', 0, NOW(6), NOW(6), 'SYSTEM', 'SYSTEM'),
    ('ROLE_BANK', b'0', 0, NOW(6), NOW(6), 'SYSTEM', 'SYSTEM'),
    ('ROLE_MERCHANT', b'0', 0, NOW(6), NOW(6), 'SYSTEM', 'SYSTEM'),
    ('ROLE_USER', b'0', 0, NOW(6), NOW(6), 'SYSTEM', 'SYSTEM');