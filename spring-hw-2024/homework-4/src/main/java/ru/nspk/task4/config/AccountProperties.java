package ru.nspk.task4.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "account.balance")
public record AccountProperties(long initialValue, boolean allowNegative) {}
