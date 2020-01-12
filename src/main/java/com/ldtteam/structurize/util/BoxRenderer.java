package com.ldtteam.structurize.util;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;

/**
 * Helper class to render boxes.
 */
public class BoxRenderer
{
    public static void drawSelectionBoundingBox(final Matrix4f matrix4f, AxisAlignedBB box, float red, float green, float blue, float alpha)
    {
        drawBoundingBox(matrix4f, (float) box.minX, (float) box.minY, (float) box.minZ, (float) box.maxX, (float) box.maxY, (float) box.maxZ, red, green, blue, alpha);
    }

    public static void drawBoundingBox(final Matrix4f matrix4f, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, float red, float green, float blue, float alpha)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        drawBoundingBox(bufferbuilder, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha, matrix4f);
        tessellator.draw();
    }

    public static void drawBoundingBox(
      BufferBuilder buffer,
      float minX,
      float minY,
      float minZ,
      float maxX,
      float maxY,
      float maxZ,
      float red,
      float green,
      float blue,
      float alpha,
    final Matrix4f matrix)
    {
        //lower base
        buffer.vertex(matrix, minX, minY, minZ).color(red, green, blue, 0.0F).endVertex();
        buffer.vertex(matrix, minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, minX, minY, minZ).color(red, green, blue, alpha).endVertex();

        //first leg
        buffer.vertex(matrix, minX, maxY, minZ).color(red, green, blue, alpha).endVertex();

        //upper base
        buffer.vertex(matrix, maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, minX, maxY, minZ).color(red, green, blue, alpha).endVertex();

        //links runter
        buffer.vertex(matrix, minX, maxY, maxZ).color(red, green, blue, 0.0F).endVertex();
        buffer.vertex(matrix, minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();

        //rechts runter
        buffer.vertex(matrix, maxX, maxY, maxZ).color(red, green, blue, 0.0F).endVertex();
        buffer.vertex(matrix, maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
    }

    /**
     * Private constructor to hide implicit one.
     */
    private BoxRenderer()
    {
        /*
         * Intentionally left empty.
         */
    }
}