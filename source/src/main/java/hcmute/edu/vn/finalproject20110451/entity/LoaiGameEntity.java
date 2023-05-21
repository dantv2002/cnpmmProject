package hcmute.edu.vn.finalproject20110451.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "loaigame")
public class LoaiGameEntity extends BaseEntity{
    @Id
    private String id;

    private String maLoai;

    private String tenLoai;

    private String moTa;

    private boolean trangThai = true;
}
