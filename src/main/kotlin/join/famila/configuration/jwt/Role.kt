package join.famila.configuration.jwt

enum class Role(description: String) {
    ADMIN("관리자"),

    USER("사용자"),

    HELPER("도우미"),

    GUEST("비회원"),
}
