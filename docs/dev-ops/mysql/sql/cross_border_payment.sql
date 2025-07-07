-- 创建数据库（如已存在可忽略此步）
CREATE DATABASE IF NOT EXISTS blockchain_payments DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE blockchain_payments;

CREATE TABLE exchange_rate (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    from_currency VARCHAR(10) NOT NULL,
    to_currency VARCHAR(10) NOT NULL,
    buy_rate DECIMAL(10,6) NOT NULL,
    sell_rate DECIMAL(10,6) NOT NULL,
    is_active BOOLEAN DEFAULT true,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_currency_pair (from_currency, to_currency)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 初始化一些示例数据
INSERT INTO exchange_rate (from_currency, to_currency, buy_rate, sell_rate, description) VALUES
('CNY', 'USD', 7.45, 7.50, 'CNY-USD Exchange Rate'),
('CNY', 'EUR', 7.80, 7.85, 'CNY-EUR Exchange Rate'),
('CNY', 'GBP', 8.65, 8.75, 'CNY-GBP Exchange Rate'),
('CNY', 'JPY', 0.064, 0.066, 'CNY-JPY Exchange Rate'),
('CNY', 'HKD', 0.90, 0.92, 'CNY-HKD Exchange Rate');