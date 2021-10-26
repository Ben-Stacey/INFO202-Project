/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import io.jooby.Jooby;
import io.jooby.Route;
import java.nio.file.Paths;

/**
 *
 * @author benstacey
 */
public class StaticAssetModule extends Jooby{
    public StaticAssetModule(){
        get("/favion.ico", Route.FAVICON);

        assets("/*", Paths.get("static"));
    }
}
