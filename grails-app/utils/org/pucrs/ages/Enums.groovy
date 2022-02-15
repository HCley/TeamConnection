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

enum AGES {
    I('I'), II('II'), III('III'), IV('IV')

    final String id
    private AGES(String id) { this.id = id }
    static AGES byId(String id) {
        values().find { it.id == id }
    }

    static List<AGES> list() {
        Arrays.asList(values());
    }
}