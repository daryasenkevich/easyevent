package com.easyevent.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "events")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", unique = true, nullable = false)
    private long id;

    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private String description;

    private int cost;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "expire_at")
    private String expiresAt;

    @ManyToMany
    @JoinTable (name="organizations_events",
            joinColumns=@JoinColumn (name="event_id"),
            inverseJoinColumns=@JoinColumn(name="organization_id"))
    private List<OrganizationEntity> organizationEntities;

    @ManyToMany
    @JoinTable (name="artists_events",
            joinColumns=@JoinColumn (name="event_id"),
            inverseJoinColumns=@JoinColumn(name="artist_id"))
    private List<ArtistEntity> artistEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return getId() == that.getId() &&
                getCost() == that.getCost() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt()) &&
                Objects.equals(getExpiresAt(), that.getExpiresAt());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getStartDate(), getEndDate(), getDescription(), getCost(), getCreatedAt(), getExpiresAt());
    }
}
