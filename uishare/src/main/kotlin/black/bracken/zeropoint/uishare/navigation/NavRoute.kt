package black.bracken.zeropoint.uishare.navigation

@JvmInline
value class NavRoute(val value: String) {
  constructor(graphName: String, destination: String) : this("${graphName}_$destination")
}