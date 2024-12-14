package net.minestom.util.annotations;

import net.minestom.Flags;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ServerFlag
{
    String value();

    Flags.ServerFlagType type();
}
