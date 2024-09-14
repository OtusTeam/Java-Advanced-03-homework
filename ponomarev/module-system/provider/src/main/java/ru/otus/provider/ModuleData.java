package ru.otus.provider;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author Anton Ponomarev on 28.05.2024
 * @project Java-Advanced-homework
 */
@Data
@Builder
public class ModuleData {
  private String data;
  private UUID uuid;
}
