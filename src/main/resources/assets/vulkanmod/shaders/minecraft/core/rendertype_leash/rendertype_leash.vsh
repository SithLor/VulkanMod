#version 450

#include "fog.glsl"

layout(location = 0) in vec3 Position;
layout(location = 1) in vec4 Color;
layout(location = 2) in ivec2 UV2;

layout(binding = 0) uniform UniformBufferObject {
   mat4 MVP;
   vec4 ColorModulator;
};

layout(binding = 2) uniform sampler2D Sampler2;

layout(location = 0) out vec4 vertexColor;
layout(location = 1) out float vertexDistance;

void main() {
    gl_Position = MVP * vec4(Position, 1.0);

    vertexDistance = fog_distance(Position.xyz, 0);
    vertexColor = Color * ColorModulator * texelFetch(Sampler2, UV2 / 16, 0);
}