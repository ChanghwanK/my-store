rootProject.name = "mystoreapplication"

include("interfaces")
include("infrastructure:db")
include("domain")
include("applications")
include("interfaces:web")
findProject(":interfaces:web")?.name = "web"
