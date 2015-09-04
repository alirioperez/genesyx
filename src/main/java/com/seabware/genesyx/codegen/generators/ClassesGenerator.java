package com.seabware.genesyx.codegen.generators;

import com.seabware.genesyx.codegen.model.Entity;
import java.io.IOException;

public interface ClassesGenerator
{
    // --------------------------------------------------------------------------------------------------------------------------
    // Generates an entity class given an Entity model.
	// --------------------------------------------------------------------------------------------------------------------------
    public void generateEntity(final Entity entity, final String path) throws IOException;

    // --------------------------------------------------------------------------------------------------------------------------
    // Generates Data Access Object class.
    // --------------------------------------------------------------------------------------------------------------------------
    public void generateDao(final Entity entity, final String path) throws IOException;
}
