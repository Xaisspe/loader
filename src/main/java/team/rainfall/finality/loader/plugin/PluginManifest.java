package team.rainfall.finality.loader.plugin;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.semver4j.Semver;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.finality.loader.util.Localization;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * The PluginManifest class represents the manifest of a plugin.
 * It contains metadata about the plugin such as its version, id, name, and other properties.
 * This class is responsible for parsing the manifest information from an input stream.
 *
 * @author RedreamR
 */
public class PluginManifest {

    public String sdkVersion;
    //Version of Plugin,should be semVer
    public String version;
    public String id;
    public String name;
    public Boolean hasTweaker;
    public String tweaker;
    public Boolean useLuminosity = false;
    public ArrayList<String> tweakClasses = new ArrayList<>();


    /**
     * Constructs a PluginManifest object by parsing the provided input stream.
     *
     * @param is the input stream containing the plugin manifest JSON data
     * @author RedreamR
     */
    public PluginManifest(InputStream is){
        JSONObject jsonObject = JSON.parseObject(is, Charset.defaultCharset());
        useLuminosity = jsonObject.getBoolean("useLuminosity");
        if(useLuminosity) {
            jsonObject.getJSONArray("tweakClasses").forEach(item -> tweakClasses.add((String) item));
        }
        sdkVersion = jsonObject.getString("sdkVersion");
        if(sdkVersion == null || Semver.isValid(sdkVersion)){
            sdkVersion = "*";
        }
        version = jsonObject.getString("version");
        name = jsonObject.getString("name");
        if(name == null){
            name = id;
        }
        if(version == null || !Semver.isValid(version)){
            FinalityLogger.warn(String.format(Localization.bundle.getString("not_a_semver"), name,version));
            version = "0.0.0";
        }
        id = jsonObject.getString("id");

        if(useLuminosity == null){
            useLuminosity = false;
        }
    }

    public PluginManifest(){}

}
