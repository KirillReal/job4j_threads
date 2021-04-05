package ru.job4j.exam;

import java.util.Objects;

public class Camera {
    private final String id;
    private final String urlType;
    private final String videoUrl;
    private final String value;
    private final String ttl;

    public Camera(String id, String urlType, String videoUrl, String value, String ttl) {
        this.id = id;
        this.urlType = urlType;
        this.videoUrl = videoUrl;
        this.value = value;
        this.ttl = ttl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Camera that = (Camera) o;
        if (!Objects.equals(id, that.id)) {
            return false;
        }
        if (!Objects.equals(urlType, that.urlType)) {
            return false;
        }
        if (!Objects.equals(videoUrl, that.videoUrl)) {
            return false;
        }
        if (!Objects.equals(value, that.value)) {
            return false;
        }
        return Objects.equals(ttl, that.ttl);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (urlType != null ? urlType.hashCode() : 0);
        result = 31 * result + (videoUrl != null ? videoUrl.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (ttl != null ? ttl.hashCode() : 0);
        return result;
    }
}
