package ru.netology.khairullina.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="operation.processing")
public class OperationConfig {
    long Sleepmillyseconds;

    public long getSleepmillyseconds() {
        return Sleepmillyseconds;
    }

    public void setSleepmillyseconds(long sleepmillyseconds) {
        this.Sleepmillyseconds = sleepmillyseconds;
    }
}
