/*
 * Copyright (C) 2010 Toni Menzel
 * Copyright (C) 2014 Guillaume Nodet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.url.mvn.internal;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.providers.file.FileWagon;
import org.apache.maven.wagon.providers.http.HttpWagon;
import org.eclipse.aether.transport.wagon.WagonProvider;
import org.ops4j.pax.url.mvn.internal.wagon.ConfigurableHttpWagon;
import org.ops4j.util.property.PropertyResolver;

/**
 * Simplistic wagon provider
 */
public class ManualWagonProvider implements WagonProvider
{

    private CloseableHttpClient client;
    private int timeout;

    public ManualWagonProvider( CloseableHttpClient client, int timeout )
    {
        this.client = client;
        this.timeout = timeout;
    }

    public Wagon lookup( String roleHint ) throws Exception
    {
        if( "file".equals( roleHint ) )
        {
            return new FileWagon();
        }
        else if( "http".equals( roleHint ) || "https".equals( roleHint) )
        {
            return new ConfigurableHttpWagon( client, timeout );
        }

        return null;
    }

    public void release( Wagon wagon )
    {
    }
}
