/*
 * Copyright (c) 2002-2018 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.index.lucene;

import org.neo4j.index.impl.lucene.explicit.LuceneIndexImplementation;
import org.neo4j.io.fs.FileSystemAbstraction;
import org.neo4j.kernel.configuration.Config;
import org.neo4j.kernel.extension.ExtensionType;
import org.neo4j.kernel.extension.KernelExtensionFactory;
import org.neo4j.kernel.impl.index.IndexConfigStore;
import org.neo4j.kernel.impl.spi.KernelContext;
import org.neo4j.kernel.lifecycle.Lifecycle;
import org.neo4j.kernel.spi.legacyindex.IndexProviders;

/**
 * @deprecated removed in 4.0
 */
@Deprecated
public class LuceneKernelExtensionFactory extends KernelExtensionFactory<LuceneKernelExtensionFactory.Dependencies>
{
    /**
     * @deprecated removed in 4.0
     */
    @Deprecated
    public interface Dependencies
    {
        Config getConfig();

        IndexProviders getIndexProviders();

        IndexConfigStore getIndexStore();

        FileSystemAbstraction fileSystem();
    }

    /**
     * @deprecated removed in 4.0
     */
    @Deprecated
    public LuceneKernelExtensionFactory()
    {
        super( ExtensionType.DATABASE, LuceneIndexImplementation.SERVICE_NAME );
    }

    @Override
    public Lifecycle newInstance( KernelContext context, Dependencies dependencies )
    {
        return new LuceneKernelExtension(
                context.directory(),
                dependencies.getConfig(),
                dependencies::getIndexStore,
                dependencies.fileSystem(),
                dependencies.getIndexProviders(),
                context.databaseInfo().operationalMode );
    }
}
