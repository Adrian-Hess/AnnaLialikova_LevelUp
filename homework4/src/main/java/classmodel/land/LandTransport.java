package classmodel.land;

import classmodel.base.PublicTransport;

public class LandTransport extends PublicTransport {
    private String route;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
