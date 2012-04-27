// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package net.sourceforge.eclipsejetty.jetty5;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sourceforge.eclipsejetty.JettyPlugin;
import net.sourceforge.eclipsejetty.JettyPluginUtils;
import net.sourceforge.eclipsejetty.jetty.IJettyLibStrategy;
import net.sourceforge.eclipsejetty.jetty.JspSupport;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Resolve libs for Jetty 5
 * 
 * @author Christian K&ouml;berl
 * @author Manfred Hantschel
 */
public class Jetty5LibStrategy implements IJettyLibStrategy
{

    /* (non-Javadoc)
     * @see net.sourceforge.eclipsejetty.IJettyLibStrategy#find(java.io.File)
     */
    public Collection<File> find(File jettyPath, JspSupport jspSupport) throws CoreException
    {
        File jettyLibDir = new File(jettyPath, "lib");
        File jettyExtDir = new File(jettyPath, "ext");

        if (!jettyLibDir.exists() || !jettyLibDir.isDirectory() || !jettyExtDir.exists() || !jettyExtDir.isDirectory())
        {
            throw new CoreException(new Status(IStatus.ERROR, JettyPlugin.PLUGIN_ID, "Could not find Jetty libs in "
                + jettyPath.getAbsolutePath()));
        }

        List<File> jettyLibs = new ArrayList<File>();

        Collections.addAll(jettyLibs, jettyLibDir.listFiles(JettyPluginUtils.JAR_FILTER));
        Collections.addAll(jettyLibs, jettyExtDir.listFiles(JettyPluginUtils.JAR_FILTER));

        return jettyLibs;
    }

}
