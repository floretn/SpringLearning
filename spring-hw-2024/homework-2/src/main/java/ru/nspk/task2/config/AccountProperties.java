package ru.nspk.task2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "account.balance")
public record AccountProperties(long initialValue, boolean allowNegative) {}
