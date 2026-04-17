package top.andyron.shushequ.service.sitemap.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author andyron
 * @date 2026/4/17
 */
@Data
@NoArgsConstructor
@JacksonXmlRootElement(localName = "url")
public class SiteUrlVo {

    @JacksonXmlProperty(localName = "loc")
    private String loc;

    @JacksonXmlProperty(localName = "lastmod")
    private String lastMod;

    @JacksonXmlProperty(localName = "changefreq")
    private String changefreq;

    @JacksonXmlProperty(localName = "priority")
    private String priority;

    public SiteUrlVo(String loc, String lastMod) {
        this.loc = loc;
        this.lastMod = lastMod;
    }

    public SiteUrlVo(String loc, String lastMod, String changefreq, String priority) {
        this.loc = loc;
        this.lastMod = lastMod;
        this.changefreq = changefreq;
        this.priority = priority;
    }
}
