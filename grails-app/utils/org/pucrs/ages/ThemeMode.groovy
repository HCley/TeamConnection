package org.pucrs.ages

enum ThemeMode {
    DARK('DARK'), WHITE('WHITE')

    final String id
    private ThemeMode(String id) { this.id = id }
    static ThemeMode byId(String id) {
        values().find { it.id == id }
    }

    static List<ThemeMode> list() {
        Arrays.asList(values());
    }
}