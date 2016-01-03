package havefun.chatter.core.network.mapper

interface Mapper<DOMAIN, REQUEST_DTO, RESPONSE_DTO> {
    fun toRequest(domain: DOMAIN): REQUEST_DTO
    fun fromResponse(dto: RESPONSE_DTO): DOMAIN
}